# What is this?

This is a really simple Ruby driver for connecting to a USB-based Crystalfontz LCD. I use this for my training course Agile Design: Beyond the Basics. It probably has no other value, but if you can use it, then feel free.

# How do I use this?

1. Plug in your LCD display. I have the (now discontinued) Crystalfontz CFA634-NFA-KU.
1. Go to directory `/dev` and look for an entry that looks like `tty.usbserial-CF006760`. I'm not sure whether the `CF006760` part changes from display to display, because I've only ever tried this with my display.
> If you don't see such an entry, then you need to install a USB/Serial device adapter driver for your operating system. I worked on Mac OS 10.8 (Mountain Lion) and installed the "VCP" driver from [http://www.ftdichip.com/Drivers/VCP.htm](http://www.ftdichip.com/Drivers/VCP.htm)
1. For a quick test, install the `serialport` gem (running `bundle` on this project will do that for you), open `irb`, then try to connect to serial port `tty.usbserial-CF006760` at 19200 baud. If you can write *anything* to that port, then this little display "driver" will probably work.
1. Go into the directory `lcd_display/lib` to run the display servers. I haven't yet bothered to remove the need for this step. You'll find both a UDP-based driver and a Sinatra app. Use whichever fits you better.

