package upnp.upnp;

import java.util.ArrayList;
import java.util.List;

import org.teleal.cling.android.AndroidUpnpService;
import org.teleal.cling.controlpoint.ActionCallback;
import org.teleal.cling.model.action.ActionInvocation;
import org.teleal.cling.model.message.UpnpResponse;
import org.teleal.cling.model.meta.Device;
import org.teleal.cling.model.meta.LocalService;
import org.teleal.cling.model.meta.Service;
import org.teleal.cling.model.types.InvalidValueException;
import org.teleal.cling.model.types.ServiceId;
import org.teleal.cling.model.types.UDAServiceId;
import org.teleal.cling.support.contentdirectory.callback.Browse;
import org.teleal.cling.support.model.BrowseFlag;
import org.teleal.cling.support.model.DIDLContent;
import org.teleal.cling.support.model.item.Item;

import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.ArrayAdapter;

public class BrowseDeviceActivity extends ListActivity {

    private Device device;
    private AndroidUpnpService upnpService;
    private Service service;
    private Service[] services;
    ArrayAdapter<String> adapter;
    List<String> items = new ArrayList<String>();

    private ServiceConnection serviceConnection = new ServiceConnection() {

        public void onServiceConnected(ComponentName className, IBinder service) {
            upnpService = (AndroidUpnpService) service;
//            控制
            control();
        }

        public void onServiceDisconnected(ComponentName className) {
            upnpService = null;
        }
    };

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // services = new ArrayList<Service>();
        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("device");

        device = ((UpnpBrowserApp) getApplication()).getDevice(position);

        items.add((String) (device.getDetails().getFriendlyName()));//Fridendly Binary Light
//        items.add((String) (device.getDetails().getSerialNumber()));
        getApplicationContext().bindService(
                new Intent(this, BrowserUpnpService.class), serviceConnection,
                Context.BIND_AUTO_CREATE);
        }

//    控制
    public void control() {
        services = device.getServices();
        if (services != null && services.length > 0) {
            items.add(services[0].getServiceId().getNamespace());//upnp-org
        }
        service = device.findService(new UDAServiceId("SwitchPower"));
        if (service == null) {
            items.add("service of the device is null");
        }

        if (upnpService == null) {
            items.add("upnpService is null");
        }

        if ((service != null) && (upnpService != null)) {
            ActionInvocation setTargetInvocation =
                    new SetTargetActionInvocation(service);

            // Executes asynchronous in the background
            upnpService.getControlPoint().execute(
                    new ActionCallback(setTargetInvocation) {

                        @Override
                        public void success(ActionInvocation invocation) {
                            assert invocation.getOutput().length == 0;
                            System.out.println("Successfully called action!");
                        }

                        @Override
                        public void failure(ActionInvocation invocation,
                                            UpnpResponse operation,
                                            String defaultMsg) {
                            System.err.println(defaultMsg);
                        }
                    }
            );
        }
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        this.setListAdapter(adapter);
    }

    //set action
    class SetTargetActionInvocation extends ActionInvocation {

        SetTargetActionInvocation(Service service) {
            super(service.getAction("SetTarget"));
            try {

                // Throws InvalidValueException if the value is of wrong type
                // set action
                setInput("NewTargetValue", true);

            } catch (InvalidValueException ex) {
                System.err.println(ex.getMessage());
                System.exit(1);
            }
        }
    }
}