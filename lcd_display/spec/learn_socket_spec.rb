require "rubygems"
gem "rspec"

require "socket"

describe "Socket client/server pair" do
  before(:each) do
    @server = nil
    @client = nil
    @received = StringIO.new
  end
  
  after(:each) do
    @server.close unless @server.nil?
    @client.close unless @client.nil?
  end
  
  it "should work when the server writes and the client reads" do
    @server = TCPServer.new("mel.local", 20000)
    server_speaks = Thread.new do
      client = @server.accept
      client.write(Time.utc(1974,5,4,0,0,0,0))
      client.close
    end

    @client = TCPSocket.open("mel.local", 20000) do |socket|
      str = socket.recv(100)
      @received.write(str)
      @received.flush
    end

    server_speaks.join
    @received.rewind

    @received.read.should == "Sat May 04 00:00:00 UTC 1974"
  end
  
  it "should work when the client writes and the server reads" do
    @server = TCPServer.new("mel.local", 2000)
    server_listens = Thread.new do
      client = @server.accept
      @received.write(client.read)
      client.close
    end
    
    @client = TCPSocket.open("mel.local", 2000) do |socket|
      socket.write("Hello, Joe!")
      socket.close
    end
    
    server_listens.join
    @received.rewind
    
    @received.read.should == "Hello, Joe!"
  end
end
