# RaspiratorMaster
It is remote client for server (see raspirator repo) with GUI. It is not completed project yet, but i work hard as i can.

Now. you can set the remote address of server and bind it on some port. the client tell you if server will not found or if connection will be lost.

So. all what you need is run server like this:

java -jar server.jar -p 4000

where -p - is flag of your port. Then run client and set the ip and port in input form. 

After successful connection you can send a command. One of this:

- help. Shows all commands and basic help
- exec. Execut system commands (see raspirator description)
- ver. Shows current version of raspirator
