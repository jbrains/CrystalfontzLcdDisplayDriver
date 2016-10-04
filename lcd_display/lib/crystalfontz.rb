require "serialport"

module Crystalfontz
  module USB
    module LCD
      # Datasheet with protocol details:
      # https://www.crystalfontz.com/products/document/343/634full_v0_7.pdf
      class Display
        def self.open(&block)
          yield self.new(SerialPort.open("/dev/tty.usbserial-CF006760", 19200))
        end

        def initialize(serial_port)
          @serial_port = serial_port
        end

        def clear_display
          @serial_port.putc 12
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
