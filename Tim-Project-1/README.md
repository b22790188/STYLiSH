# STYLiSH

[WebURL](http://52.69.33.14/)

## Enable MFA and Create IAM User

1. Log in as the root user.
2. Click on the account menu in the upper right corner -> Select Security Credentials.
3. Enable MFA.
4. Select Users from the Access Management menu on the left.
5. Create an IAM user.
6. Attach the policy directly to the user.
7. Select `Administrator Access` for access permissions.

## Add Security Group

1. Click on the left menu -> Network & Security -> Security Groups.
2. Add a security group that includes SSH and HTTP connections.
3. Modify the instance's security group:
   - `Inbound rule` -> ssh, http, https
   - `Outbound rule` -> ssh, http, https

## Launch EC2 Instance and Associate Elastic IP

1. Create a key pair.
2. Launch an EC2 instance, choose the appropriate specifications as per `README.md`, and select the previously created `Security group`.
3. From Network & Security, select Elastic IP and create it, then associate it with the desired instance.

## Connect via SSH

```sh
ssh -i <filename>.pem ubuntu@<user-ip>
```

## Java Installation

```sh
sudo apt update
```

```sh
sudo apt install openjdk-17-jre-headless
```

## Pull Code from github

```sh
git pull git@github.com:b22790188/Back-End-Class-Tech-Academy.git
```

## Open Port on 80 (authbind method)

1. Use authbind to grant permissions

```sh
sudo touch /etc/authbind/byport/80
sudo chmod 500 /etc/authbind/byport/80
sudo chown your-username /etc/authbind/byport/80
```

- Use authbind to grant permissions:
- Change the owner to yourself, preventing others from accessing it.

2. Enter the stylish directory and package the jar file using Maven:

```sh
mvn clean package
```

3. Start the jar file as a normal user:

```sh
authbind --deep java -jar your-application.jar

```

## Open Port(nginx method)

1. `Create a configuration file in `/etc/nginx/sites-available.`
2. Write the `nginx` file:

```nginx
server {
    listen 80;
    server_name <domain_name or IP>;

    location / {
        proxy_set_header X-Forwarded-For $remote_addr;
        proxy_set_header Host $http_host;
        proxy_pass       "http://127.0.0.1:8080";
    }
}

```

3. Create a soft link to `/etc/nginx/sites-enabled`

```sh
sudo ln -s /etc/nginx/sites-available/node /etc/nginx/sites-enabled/node
```

4. Restart the nginx server:

```sh
sudo service nginx restart
```

## Running Web Server in Background

- Using `nohup`

```sh
nohup java -jar <filename>.jar
```
