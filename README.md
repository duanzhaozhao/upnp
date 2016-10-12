# upnp
upnp控制点控制设备
依赖库cling-core,cling-support,teleal-common
UPNP
一、UPNP简介
UPnP是由“通用即插即用论坛”（UPnP™ Forum）推广的一套网络协议。该协议的目标是使家庭网络（数据共享、通信和娱乐）和公司网络中的各种设备能够相互无缝连接，并简化相关网络的实现。UPnP通过定义和发布基于开放、因特网通讯网协议标准的UPnP设备控制协议来实现这一目标。
UPnP体系允许 PC 间的点对点连接、网际互连和无线设备。它是一种基于TCP/IP、UDP和HTTP的分布式、开放体系。
二、 UPnP的特点
UPnP使得任意两个设备能在LAN控制设备的管理下相互通信。其特性包括：
1.传输介质和设备独立。
UPnP技术可以应用在许多媒体上，包括电话线、电线（电力线通信PLC）、以太网、红外通信技术(IrDA)、无线电（Wi-Fi，蓝牙）和Firewire(1394)。无需任务设备驱动；而是采用共同的协议。
2.用户界面（UI）控制。
UPnP 技术使得设备厂商可以通过网页浏览器来控制设备并进行交互。
3.操作系统和程序语言独立。
任何操作系统和程序语言均可以用于构建UPnP产品。UPnP并没有设定或限制运行于控制设备上的应用程序API；OS厂商可以创建满足他们客户需求的API。UPnP使得厂商可以像开发常规应用程序一样来控制设备UI和交互。
4.基于因特网技术。
UPnP 构建于 IP, TCP, UDP, HTTP，和 XML 等许多协议之上。
5.编程控制。
UPnP 体系同时支持常规应用程序编程控制。
6.扩展性。
每个UPnP设备都可以有构建于基本体系之上、与具体设备相关的服务。UPnP 支持零配置，"看不见的网络" 和自动检测；任何设备能自动加入一个网络， 获取一个 IP地址，宣布自己的名字，根据请求检查自身功能以及检测出其它设备 和它们的功能。DHCP 和 DNS 服务是可选的，并只有它们在网络上存在的时候才会 使用。设备可以自动离开网络而不会遗留下任何不需要的状态信息。
UPnP的基础是 IP地址解析。每一个设备都应当有一个DHCP客户端并在连入网 络的时候自动搜索DHCP服务。如果没有找到DHCP服务，也就是说网络是缺乏管 理状态，那么设备必须给自己设定一个地址。如果在和DHCP服务器交互的过程中，设备获得了一个域名（比如通过 DNS 服务器或者 DNS 传递），那么它应当在接下来的网络操作中使用这个域名；否则，设备应当使用它的IP地址。
三、UPnP的基本组件
设备、服务和控制点是UPnP网络的基本组件，它们之间的关系图下图所示：
 
3.1 设备（Device）
UPnp网络中定义的设备具有很广泛的含义，各种各样的家电、电脑外设、智能设备、无线设备、个人电脑等等都可以称之为设备。一台UPnP设备可以是多个服务的载体或多个子设备的嵌套。
3.2 服务（Service）
在UPnP网络中，最小的控制单元就是服务。服务描述的是指设备在不同情况下的动作和设备的状态。例如，时钟服务可以表述为时间变化值、当前的时间值以及设置时间和读取时间两个活动，通过这些动作，就可以控制服务。
3.3 控制点（Control Point）
在UPnP网络中，控制点指的是可以发现并控制其他设备的控制设备。在UPnP网络中，设备可以和控制点合并，为同一台设备，同时具有设备的功能和控制点的功能，即可以作为设备提供服务，也可以作为控制点发现和控制其他设备。
四、UPnP协议栈
UPnP定义了设备之间、设备和控制点、控制点之间通信的协议。完整的UPnP有设备寻址、设备发现、设备描述、设备控制、事件通知和基于Html的描述等几部分构成。UPnP设备协议栈如下图所示：
 
UPnP协议结构最底层的TCP/IP协议是UPnP协议结构的基础。IP层用于数据的发送与接收。对于需要可靠传送的信息，使用TCP进行传送，反之则使用UDP。UPnP对网络的底层没有要求，可以是以太网、WIFI、IEEE1394等等，只需支持IP协议即可。
构建在TCP/IP协议之上的是HTTP协议及其变种，这一部分是UPnP的核心，所有UPnP消息都被封装在HTTP协议及其变种中。HTTP协议的变种是HTTPU和HTTPMU，这些协议的格式沿袭了HTTP协议，只不过与HTTP不同的是他们通过UDP而非TCP来承载的，并且可用于组播进行通信。
4.1 SSDP协议
简单服务发现协议（Simple Service Discovery Protocol：SSDP），是内建在HTTPU/HTTPMU里，定义如何让网络上有的服务被发现的协议。具体包括控制点如何发现网络上有哪些服务，以及这些服务的资讯，还有控制点本身宣告他提供哪些服务。该协议运用在UPnP工作流程的设备发现部分。
4.2 SOAP协议
简单对象访问协议（Simple Object Access Protocol：SOAP）定义如何使用XML与HTTP来执行远程过程调用（Remote Procedure Call）。包括控制点如何发送命令消息给设备，设备收到命令消息后如何发送响应消息给控制点。该协议运用在UPnP工作流程的设备控制部分。
4.3 GENA协议
通用事件通知架构（Generic Event Notification Architecture：GENA）定义在控制点想要监听设备的某个服务状态变量的状况时，控制点如何传送订阅信息并如何接收这些信息，该协议运用在UPnP工作流程的事件订阅部分。
五. UPnP的工作流程
下图是UPnP的运行流程，我们先大概介绍下
 
1、 首先控制点和设备都先获取IP地址后才能进行下一步的工作；
2、 控制点首先要寻找整个网络上的UPnP设备，同时网络上的设备也要宣告自身的存在；
3、 控制点要取得设备的描述，包括这些设备提供什么样的服务；
4、 控制点发出动作信息给设备；
5、 控制点监听设备的状态，当状态改变时作出相应的处理动作；
5.1 寻址（Addressing）
UPnP网络的基础是TCP/IP，这就决定了每一个UPnP组件必须有IP地址。一台UPnP设备寻址的一般过程是：首先向DHCP服务器发送DHCP Discover的消息，如果在指定的时间内，设备没有收到DHCP Offer回应消息，设备必须使用AUTO-IP完成IP地址的获取。当然也可以使用静态配置的IP地址。
5.2 发现（Discovery）
连接到网络上的设备确定了IP地址之后，就会进入发现操作阶段。设备发现是UPnP实现的第一步。设备发现是由简单发现协议SSDP来完成的。当一台设备加入到网络中，发现过程允许设备向网络上的控制节点告知它提供的服务，当一个控制点加入到网络中，设备发现过程允许控制点寻找网络上感兴趣的设备。在这两种情况下，基本的交换信息就是发现消息。发现消息包括设备的一些特定信息或者某项服务的信息，例如它的类型、标志符、等等。下图是发现流程的框架图：
 
5.3 描述（Description）
UPnP的第二步是设备描述。在控制点发现一台设备后，控制点对该设备可能仅仅知道设备或者服务的UPnP类型，设备的UUID和设备描述的URL地址，还需要知道更多的信息。控制点可以从发现消息中得到设备描述的URL，通过URL取回设备描述的信息。设备描述的一般过程图如下图所示：

 




设备和服务的描述信息一般模型：
 
设备描述
UPnP对某一设备的描述以XML形式来表示，设备描述包括制造商信息、模块名称和编号、序列号等等。对于一个物理设备可以包含多个逻辑设备，多个逻辑设备既可以是一个根设备其中嵌入多个设备，也可以是多个根设备的方式存在。设备描述由设备制造商提供，采用XML描述，遵循UPnP框架协议。
服务描述
服务的描述包含一系列内容，具体有服务运行时刻的状态，运行时间等等。服务描述也由设备制造商提供，采用XML描述，遵循UPnP框架协议。
5.4 控制（Control）
在接收设备和服务描述之后，控制点可以向这些服务发出动作，同时控制点也可以轮询服务的当前状态。控制点将动作发送到设备服务，在动作完成或者失败后，服务返回相应的结果或者错误信息。其基本过程如下图所示：
 
为了控制一台设备，控制点向设备服务发出一动作，这一般是由控制点向服务的控制URL地址发送一个适当的控制消息。而服务则会对此动作出响应，返回相关的结果或错误。
5.5 事件（Even ting）
如上文的描述部分所述，一个即插即用服务描述包括服务响应的动作列表和运行时描述服务状态的变量列表。如果一个或多个状态被事件触发，服务将会在这些状态发生变化时发布更新，控制点可以订阅以获得此信息。在事件机制中，发布者指事件的来源（通常为设备服务），订阅者指事件目的地（通常为控制点）。
要订阅事件，订阅者可发送一条请求订阅消息。它将以这个订阅到持续时间作为响应。要保持订阅，订阅者必须在订阅过期之前进行续订。当订阅者不再需要发布者发送的事件时，订阅者应当取消其订阅。
发布者通过发送事件消息提醒订阅者状态改变。在订阅者第一次订阅时，需要发送一个专门的初始化事件消息。该事件消息包含所有事件的名称和值，并且允许订阅者初始化其服务状态。为了支持多个控制点，在动作生效后所有订阅者均会收到通知。由此，将向所有订阅者发送全部事件消息。事件消息使用HTTP协议传送，事件详细定义在通用事件通知结构（GENA）协议中。
5.6 展示（Presentation）
在控制点发现设备和取得设备描述之后，展示也就开始了。如果设备拥有进行展示的URL，那么控制点就可以通过此URL取得一个页面，在浏览器中加载该页面，并根据页面功能，支持用户控制设备或浏览设备状态。每一项完成的程度取决于展示页面和设备的具体功能。
设备展示包含在设备描述的Presentation URL字段。设备展示可以完全由设备制造商提供，它采用HTML页的形式，使用HTTP进行发布。下图是展示的流程示意图：
 
六、The Cling Core API
 
1.	UPnPServiceImpl 
  创建upnp协议栈
2.	UpnpServiceConfiguration   
 创建UPnPServiceImpl默认配置
3. ActionInvocation   提供了一系列方法用于控制设备设置输入，可以获得设备的返回值
4. ControlPoint  
控制点的接口，主要功能是异步执行搜索，设备控制订阅等指令。
此接口定义了查找设备，向设备发送指令，订阅设备变更，其实现类只有一个为 ControlPointImpl。
(1) 查找
public void search(UpnpHeader searchType, int mxSeconds);
第一个参数UpnpHeader表示查询条件，第二个参数表示最大超时时间，以秒为单位。
UpnpHeader 是一个抽象类，其中定义了包含每个过程请求中的 Header 信息的枚举类型Type以及泛型 value，查询时常用的实现类有：DeviceTypeHeader，UDNHeader 等，可根据设备类型、UDN、服务类型等多种方式。
(2) 执行控制指令
public Future execute(ActionCallback callback)
将 ActionCallback 放入 DefaultUpnpServiceConfiguration 中定义的线程池 ClingExecutor 执行，执行完毕回调 ActionCallback 中定义的 success 或 failure 函数。
ActionCallback 是命令执行的回调接口，在其 run 方法内会根据是本地命令还是远程命令进行执行，执行结束后回调成功或失败接口。
(3) 执行事件订阅指令
public void execute(SubscriptionCallback callback)
将 SubscriptionCallback 放入 DefaultUpnpServiceConfiguration 中定义的线程池 ClingExecutor 执行，执行完毕回调 ActionCallback 中定义的 established、failed、ended 等函数。

5. ProtocolFactory
UPnP 协议的工厂类，用于根据收到的 UPnP 协议或是本地设备的 meta 信息，创建一个可执行的协议。
使用简单工厂模式封装协议内容的处理，实现类为 ProtocolFactoryImpl，主要根据接收报文和发送报文两大类创建不同协议。
在该类中 UDP 包通过 createReceivingAsync 方法对传递来的 IncomingDatagramMessage 进行处理，如 NOTIFY--ReceivingNotification，MSEARCH--ReceivingSearch。
TCP 包通过 createReceivingSync 进行分发处理，并通过 ReceivingSync 的子类进行处理，子类中调用 executeSync 方法等待并返回 response。
(1) 处理接收到的报文
public ReceivingAsync createReceivingAsync(IncomingDatagramMessage message)
IncomingDatagramMessage 封装了 UDP 包的信息，在 createReceivingAsync 中根据消息的操作类型及方法创建不同的 ReceivingAsync 子类对象，ReceivingAsync 子类通过重写 execute 方法定义具体实现。如请求的 NOTIFY 信息创建 ReceivingNotification，请求的 MSEARCH 创建 ReceivingSearch。
public ReceivingSync createReceivingSync(StreamRequestMessage message)
StreamRequestMessage 封装 TCP 报文，在 createReceivingSync 中根据消息的操作类型方法及 UPnP 服务 NameSpace 等的配置创建不同的 ReceivingSync 的子类对象，ReceivingSync 子类通过重写 executeSync 方法定义具体实现。
(2) 组装发送的报文
有若干功能类似的方法，返回不同的 SendingAsync 子类对象，通过重写 executeSync 方法定义具体实现。如：
a. 向组播发送 ssdp:alive 告知设备存活
public SendingNotificationAlive createSendingNotificationAlive(LocalDevice localDevice)
b. 生产 SendingSearch 实例的工厂方法，SendingSearch 中定义了查询条件以及请求超时时间，在重写的 execute 函数中，在线程启动后创建 OutgoingSearchRequest 对象并通过 Router 发送。
public SendingSearch createSendingSearch(UpnpHeader searchTarget, int mxSeconds)
6. Registry
设备资源管理器，用于设备、资源、订阅消息的管理，包括添加、更新、移除、查询。可将新设备时加入 Registry 中，在设备失效后从 Registry 中移除。目前实现类为 RegistryImpl。
关联类包括：RegistryListener、Resource、RegistryItem、RegistryItems、LocalItems、RemoteItems、ExpirationDetails、RegistryMaintainer。
7. RegistryListener
设备状态监听类，包含本地/远程设备的发现、添加、更新、移除等回调函数。可通过
addListener(RegistryListener listener)
添加，保存在RegistryListener的 Set\ registryListener 参数内。
实现类有空实现的 DefaultRegistryListener 以及通过注入属性实现的 RegistryListenerAdapter。
8. Router
数据传输层接口，负责接收和发送 UPnP 和 UDP 消息，或者将接收到的数据流广播给局域网内的其他设备。
目前实现类为 RouterImpl 和 MockRouter，其中 MockRouter 仅用来作为测试时的 Mock 接口，RouterImpl 作为默认的数据传输层实现。


创建设备过程
首先创建服务，一个服务只是一些元数据描述，必须使用ServiceManager来完成实际的动作，erviceManager是元数据和服务之间的连接，服务的动作一旦被执行，DefaultServiceManager 会实例化一个Service类，创建服务之后创建设备并绑定服务到此设备上，最后将设备添加到upnp协议栈的注册表里，就可被其他控制点发现。
 
创建控制点过程
通过upnpService.getControlPoint()得到控制点，然后给控制点添加监听器
upnpService.getRegistry().addListener(registryListener)，当有设备加入网络中时监听器对其进行处理，或者控制点通过调用search()方法主动去搜索设备，然后给搜索到的设备发送控制命令，upnpService.getControlPoint().execute(
        new ActionCallback(setTargetInvocation)执行完会回调ActionCallback的success或fail方法。



 



 


