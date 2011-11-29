require "serialport"
require "socket"

SerialPort.open("/dev/tty.usbserial-CF006760", 19200) do |serial_port|
  server = UDPSocket.new
  server.bind(nil, 5358)
  puts "Started UDP server"
  loop do
    text, sender = server.recvfrom(1024)
    puts "Sender #{sender} sent text #{text}"
    puts "Writing text..."
    [17, 0, 0].each { |each| serial_port.putc(each) }
    serial_port.write(" " * 80)
    [17, 0, 0].each { |each| serial_port.putc(each) }
    serial_port.write(text)
    puts "Wrote text"
    puts "-----"
  end
end

