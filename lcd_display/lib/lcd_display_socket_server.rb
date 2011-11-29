require "socket"
require 'serialport'

def open_server
  TCPServer.open(5357)
end

def write_text_to_serial_port(text, serial_port)
  [17, 0, 0].each { |each| serial_port.putc each }
  serial_port.write " " * 80
  [17, 0, 0].each { |each| serial_port.putc each }
  serial_port.write(text)
end

SerialPort.open("/dev/tty.usbserial-CF006760", 19200) do |serial_port|
  lcd_display_server = open_server()
  server_type = "TCP server"
  puts "Started #{server_type}"
  loop do
    Thread.start(lcd_display_server.accept) do |client|
      puts "Accepted client #{client.inspect}"
      text = client.read
      puts "Received text #{text}"
      puts "Writing text..."
      write_text_to_serial_port(text, serial_port)
      puts "Wrote text"
      puts "-----"
      client.close
    end
  end
end
