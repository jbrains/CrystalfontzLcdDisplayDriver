require "rubygems"
gem "rspec"

describe "Socket client/server pair" do
  context "when the server writes and the client reads" do
    it "should complete the communication correctly" do
      require "socket"
      dts = TCPServer.new('localhost.local', 20000)
      server_speaks = Thread.new do
        Thread.start(dts.accept) do |s|
          print(s, " is accepted\n")
          s.write(Time.now)
          print(s, " is gone\n")
          s.close
        end
      end

      streamSock = TCPSocket.new( "localhost.local", 20000 )
      #streamSock.send( "Hello\n" )
      str = streamSock.recv( 100 )
      print str
      streamSock.close
    end
  end
end
