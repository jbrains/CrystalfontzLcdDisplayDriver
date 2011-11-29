require "serialport"
require "socket"

def open_udp_server(port)
  server = UDPSocket.new
  server.bind(nil, port)
  server
end

def write_text_to_serial_port(text, serial_port)
  [17, 0, 0].each { |each| serial_port.putc each }
  serial_port.write " " * 80
  [17, 0, 0].each { |each| serial_port.putc each }
  serial_port.write(text)
end

SerialPort.open("/dev/tty.usbserial-CF006760", 19200) do |serial_port|
  udp_port = 5358
  lcd_display_server = open_udp_server(udp_port)
  puts "Started UDP server on port #{udp_port}"
  loop do
    text, sender = lcd_display_server.recvfrom(1024)
    puts "Accepted client #{sender.inspect}"
    puts "Received text #{text}"
    puts "Writing text..."
    write_text_to_serial_port(text, serial_port)
    puts "Wrote text"
    puts "-----"
  end
end

