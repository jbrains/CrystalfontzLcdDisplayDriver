require "socket"
require "./crystalfontz"

def open_udp_server(port)
  server = UDPSocket.new
  server.bind("localhost", port)
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

run_server(:port => (ARGV[0] || 5358).to_i)

