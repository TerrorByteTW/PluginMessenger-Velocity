## Plugin Messenger - Velocity

This plugin was written for my [Velocity plugin development tutorial series](https://www.youtube.com/playlist?list=PL51GdsaNM19JlY0t6KNyi4vtkC5Q-4-F4) and designed to be paired with the Plugin Messenger - Paper plugin.
Plugin messaging has been around since Minecraft 1.1, which was released in 2012, over 11 years ago!

This plugin is extremely simple, it only listens for a "RequestCreeper" message on the `demo:main` channel, and replies to all servers with a "SpawnCreeper" message. The paper plugin handles the rest

It demos the following concepts:
1. Working with Byte Streams and data outputs/inputs
2. Registering custom channel identifiers with the Velocity Channel Registrar
3. Listening to and responding with plugin messages.

For more information related to Plugin Messaging, please see the following links
* https://wiki.vg/Plugin_channels
* https://web.archive.org/web/20220711204310/https://dinnerbone.com/blog/2012/01/13/minecraft-plugin-channels-messaging/

I hope you learned something!
- Nate (TerrorByte)