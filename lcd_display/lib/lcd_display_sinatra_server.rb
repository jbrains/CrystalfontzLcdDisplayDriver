require "rubygems"
require "sinatra"
require File.join(File.expand_path(File.dirname(__FILE__)), "crystalfontz")

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
          <textarea name="text" maxlength="80" rows="4" cols="20"></textarea>
          <input type="submit" />
        </form>
      </body>
    </html>
HTML
  end
end
