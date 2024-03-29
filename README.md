# What is this?

This is a really simple Ruby driver for connecting to a USB-based Crystalfontz LCD. I use this for my training course Agile Design: Beyond the Basics. It probably has no other value, but if you can use it, then feel free.

## Update Ruby 3

The HTTP server in this project is `shotgun`. As of December 1, 2022, `shotgun` is not guaranteed to run on Ruby 3. I have no plans to upgrade this project to run on Ruby 3.

## Update 2020

I no longer own this LCD, so I have no idea whether this code is still helpful. If you have a Crystalfontz device and this code helps you, then **please tell me which device you have**. Did you have to change the code? How? I might want to buy a new LCD and that information could be handy. Thanks.

I also no longer use Mac OS as my primary operating system. I am not able to write instructions for how to use this code with any other operating system. I hope that my instructions below provide a helpful example so that you an get things running on your system. Good luck!

# How do I use this?

1. Plug in your LCD display. I have the (now discontinued) Crystalfontz CFA634-NFA-KU.
2. Go to directory `/dev` and look for an entry that looks like `tty.usbserial-CF006760`. I'm not sure whether the `CF006760` part changes from display to display, because I've only ever tried this with my display.
> If you don't see such an entry, then you need to install a USB/Serial device adapter driver for your operating system. I worked on Mac OS 10.8 (Mountain Lion) and installed the "VCP" driver from [http://www.ftdichip.com/Drivers/VCP.htm](http://www.ftdichip.com/Drivers/VCP.htm)

>   In 2016, for a reason I don't yet understand, I had to load the USB/Serial device adapter driver kernel extension myself. If you install the driver, but don't see a file matching `/dev/tty.usbserial-*` , then you might need to load the kernel extension yourself.
>
>   ```bash
>   $ sudo kextload /Library/Extensions/FTDIUSBSerialDriver.kext
>   ```
>
>   I'm assuming that the FTDI drivers installed to the typical location, `/Library/Extensions`.
>
>   (Reference: <http://www.club.cc.cmu.edu/~mdille3/doc/mac_osx_serial_console.html>)

1. For a quick test, install the `serialport` gem (running `bundle install` on this project will do that for you), open `irb`, then try to connect to serial port `tty.usbserial-CF006760` at 19200 baud. If you can write *anything* to that port, then this little display "driver" will probably work.
2. Go into the directory `lcd_display/lib` to run the display servers. I haven't yet bothered to remove the need for this step. You'll find both a UDP-based driver and a Sinatra app. Use whichever fits you better.

## UDP Server

1. To run the UDP server:

```
$ cd lcd_display/lib
$ ruby lcd_display_udp_server.rb [port:default 5358]
```

1. You can send UDP messages using netcat. `man nc` for details. Thanks to Chris at [http://link.jbrains.ca/1Bmsaa9](http://link.jbrains.ca/1Bmsaa9) for the tip!

```
$ nc -4u localhost [port]
<Type your messages into stdin.>
```

## HTTP Server

1. To run the HTTP server:

```
# Stay in the root of this project
$ shotgun
```

1. Visit [http://localhost:9393/](http://localhost:9393/) to get a web form.
2. Post data to [http://localhost:9393/display](http://localhost:9393/display) to send text to the display. `/display` expects a single parameter `text` containing the text to display.

```
$ wget --post-data 'text=Hello, World.' http://localhost:9393/display
```
