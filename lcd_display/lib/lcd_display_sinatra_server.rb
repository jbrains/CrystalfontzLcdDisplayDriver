require "rubygems"
require "sinatra"
require "./crystalfontz"

set :environment, :development
enable :logging

class LcdDisplayServer < Sinatra::Base
  post '/display' do
    text = params[:text]
    begin
      Crystalfontz::USB::LCD::Display.open() do | display |
        display.print_message(text)
      end
      redirect "/"
    rescue
      return $!
    end
  end

  get '/' do
    <<-HTML
    <html>
      <head><title>LCD Display Client</title></head>
      <body>
        <form action="/display" method="post">
          <label for="text" accesskey="m">Message</label>
          <input type="textarea" name="text" rows="4" cols="20" />
          <input type="submit" />
        </form>
      </body>
    </html>
HTML
  end

  get '/message/:text' do
    puts params[:text]
    text = params[:text]
    begin
      Crystalfontz::USB::LCD::Display.open() do | display |
        display.print_message(text)
      end
      return "Displayed!"
    rescue
      return $!
    end
  end
end

LcdDisplayServer.run!
