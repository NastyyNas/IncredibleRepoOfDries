# Switching

## Configure SSH

### save running config

```bash
copy running-config startup-config
```

### encrypt passwords

```bash
service password-encryption
```

### set the ip domain

```bash
ip domain-name yourdomainname.com
```

### generate RSA keys using a 1024 key length

```bash
crypto key generate rsa general-keys modulus 1024
```

### create an admin user

```bash
username admin privilege 15 secret cisco
```

### Configure the VTY lines to check the local username database for login credentials and to only allow SSH for remote access. Remove the existing vty line password.

```bash
line vty 0 15
no password
transport input ssh
login local
```

## Configure Router Interfaces

### configure ipv4 address router

```bash
conf t
int g0/0 # for example can be any port
ip address 172.16.20.129 255.255.255.128 # example ip address + subnet address
no shutdown
```

### configure ipv6 address router

```bash
conf t
int g0/0 # for example can be any port
ipv6 address 2001:db8:c0de:11::1/64 # example ip address/prefix
no shutdown
```

### configure link local address

```bash
conf t
int s0/0/0 # for example can be any port
ipv6 address fe80::2 link-local # example of link local address
```

### show interfaces with ip addresses

```bash
show ip int brief
show ipv6 int brief
```

## Implement a Small Network

### configure encrypted password

```bash
enable secret yourpassword
```

### configure password on the lines

```bash
line console 0
password yourpassword
login
```

### all the lines accept connections

```bash
transport input all
```

### configure banner message of the day

```bash
banner motd message
```

### description on the interfaces

```bash
description yourdescription
```

### configure default gateway

```bash
ip default-gateway 10.10.10.1
```
