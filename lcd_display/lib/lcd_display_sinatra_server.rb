require "rubygems"
require "sinatra"
require "./crystalfontz"

class LcdDisplayServer < Sinatra::Base
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
