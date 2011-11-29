require "serialport"
require "socket"

def open_server
  server = UDPSocket.new
  server.bind(nil, 5358)
  server
end

SerialPort.open("/dev/tty.usbserial-CF006760", 19200) do |serial_port|
  lcd_display_server = open_server()
  server_type = "UDP server"
  puts "Started #{server_type}"
  loop do
    text, sender = lcd_display_server.recvfrom(1024)
    puts "Accepted client #{sender.inspect}"
    puts "Received text #{text}"
    puts "Writing text..."
    [17, 0, 0].each { |each| serial_port.putc(each) }
    serial_port.write(" " * 80)
    [17, 0, 0].each { |each| serial_port.putc(each) }
    serial_port.write(text)
    puts "Wrote text"
    puts "-----"
  end
end

