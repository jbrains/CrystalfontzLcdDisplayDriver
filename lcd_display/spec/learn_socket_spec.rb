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

    @received.read.should == "1974-05-04 00:00:00 UTC"
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

describe "UDP Server" do
  it "should do something" do
    @text = ""

    @server = UDPSocket.new
    @server.bind("127.0.0.1", 5358)
    server_listens = Thread.new do
      @text, sender = @server.recvfrom(80)
    end

    @client = UDPSocket.new
    @client.send("Hello, world!", 0, "127.0.0.1", 5358)
    @client.close

    server_listens.join
    @text.should == "Hello, world!"
  end
end
