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

# VLANs

## Configure VLAN on switch

### create and name VLAN

```bash
vlan 10
name vlan-name
```

### assign port to vlan

```bash
int f0/11
switchport mode access
switchport access vlan 10
```

### assign VOICE VLAN to port

```bash
int f0/11
switchport voice vlan 150
```

### voice traffic to IP phone

```bash
int f0/11
mls qos trust cos # enable QoS and trust the Class of Service
switchport voice vlan 150
```

### VLAN overview

```bash
show vlan brief
```

## configure static trunking on switch

### Configure G0/1 and G0/2 interfaces for trunking and configure native vlan

```bash
int range g0/1-2
switchport mode trunk
switchport trunk native vlan 99
```

### trunking overview

```bash
show interface trunk
```

## Configure Dynamic trunking (DTP) on switch

### configure trunk link to dynamic desirable

```bash
int g0/1
switchport mode dynamic desirable
```

### disable DTP

```bash
int g0/2
switchport mode trunk
switchport nonegotiate
```

## Router on a stick

### Create a subinterface

```bash
int g0/0.10
```

### set the encapsulation type to 802.1Q and assign VLAN 10 to the subinterface

```bash
encapsulation dot1Q 10
```

### set the native vlan for a subinterface with encapsulation

```bash
encapsulation dot1Q 1 native
```

## inter vlan routing

### configure interface as a routing port

```bash
int g0/2
no switchport
```

### enable ip routing

```bash
ip routing
```

### enable ipv6 unicast-routing

```bash
ipv6 unicast-routing
```
