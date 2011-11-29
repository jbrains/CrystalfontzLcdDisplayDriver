require "serialport"

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
