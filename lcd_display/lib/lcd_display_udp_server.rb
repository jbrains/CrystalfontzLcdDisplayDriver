require "serialport"
require "socket"

module Crystalfontz
  module USB
    module LCD
      class Display
        def self.open(&block)
          yield self.new(SerialPort.open("/dev/tty.usbserial-CF006760", 19200))
        end

        def initialize(serial_port)
          @serial_port = serial_port
        end

        def clear_display
          write_to_display(" " * (4 * 20))
        end

        def write_to_display(text)
          [17, 0, 0].each { |each| @serial_port.putc each }
          @serial_port.write(text)
        end

        def print_message(message)
          text = message.to_s
          clear_display
          write_to_display(text)
        end
      end
    end
  end
end

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

run_server(:port => ARGV[0].to_i || 5358)

