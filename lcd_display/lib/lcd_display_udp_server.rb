require "socket"
require "./crystalfontz"

def open_udp_server(port)
  server = UDPSocket.new
  server.bind("0.0.0.0", port)
  server
end

def run_server(options)
  Crystalfontz::USB::LCD::Display.open() do | display |
    udp_port = options[:port].to_i
    lcd_display_server = open_udp_server(udp_port)
    puts "Started UDP server on port #{udp_port}"
    loop do
      text, sender = lcd_display_server.recvfrom(1024)
      puts "Accepted client #{sender.inspect}"
      puts "Received text #{text}"
      puts "Writing text..."
      display.print_message(text)
      puts "Wrote text"
      puts "-----"
    end
  end
end

begin
  run_server(:port => (ARGV[0] || 5358).to_i)
rescue Errno::ENOENT => logged
  puts "Is the display plugged in...? Well, plug it in."
  puts
  puts logged.inspect
  puts logged.backtrace
end
