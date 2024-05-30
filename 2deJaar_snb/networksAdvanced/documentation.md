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

### configure privileged exec mode password

```bash
enable secret cisco
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

# STP (Spanning Tree Protocol)

## Config STP

### give vlan a priority

```bash
conf t
spanning-tree VLAN xx priority 4096 (lowest prio)
```

### give vlan automatic priority

```bash
conf t
spanning-tree VLAN xx root primary
```

or

```bash
conf t
spanning-tree VLAN xx root secondary
```

## show/verify

```bash
show spanning-tree
show spanning-tree active
show spanning-tree vlan xx
```

# Etherchannel

## Configure Etherchannel

### protocol PAgP

do this for the same port on both switches

```bash
int range f0/21-22
shutdown
channel-group 1 mode desirable
no shutown

int port-channel 1
switchport mode trunk
```

### protocol LACP

do this for the same port on both switches

```bash
int range g0/1-2
shutdown
channel-group 2 mode active
no shutdown

int port-channel 2
switchport mode trunk
```

### protocol negotiated LACP

switch 1 is passive

```bash
int range g0/1-2
shutdown
channel-group 2 mode passive
no shutdown

int port-channel 2
switchport mode trunk
```

switch 2 is active

```bash
int range g0/1-2
shutdown
channel-group 2 mode active
no shutdown

int port-channel 2
switchport mode trunk
```

in this case switch2 negotiates with switch1 but switch1 does not negotiate with switch2

### show commando

```bash
show etherchannel summary
```

# DHCP

## Configure DHCP

### configure R2 to exclude first 10 addresses from the R1 LAN

in the config of R2
```bash
ip dhcp excluded-address 192.168.10.1 192.168.10.10
```

### create a DHCP pool

```bash
ip dhcp pool R1-LAN
```

### configure the DHCP pool to include the network addressm the default gateway and the IP address of the DNS server

```bash
network 192.168.10.0 255.255.255.0
default-router 192.168.10.1
dns-server 192.168.20.254
```

### configure the helper address for the LAN interface

```bash
interface g0/0
ip helper-address 10.1.1.2 (ip of the connection to the main router)
```

### configure interface to receive ip addressing from DHCP

```bash
interface g0/1
ip address dhcp
no shutdown
```

### verify DHCP bindings

```bash
show ip dhcp binding 
```

# Security

## Implement Port Security

### enable port security

```bash
switchport port-security
```

### set the maximum so that only one device can access the ports

```bash
switchport port-security maximum 1
```

### secure the ports so that the MAC address of a device is dynamically learned and added to the running config

```bash
switchport port-security mac-address sticky
```

### set the violation mode so that the ports are not disabled when a violation occurs

```bash
switchport port-security violation restrict
```

## switch security configuration

### statically configure MAC address using port security

```bash
switchport port-security mac-address 0010.11E8.3CBB
```

### configure trunk ports as trusted ports

```bash
ip dhcp snooping trust
```

### limit untrusted ports to 5 packets per second

```bash
ip dhcp snooping limit rate 5
```

### enable dhcp snooping globally and for vlans

in the config
```bash
ip dhcp snooping
ip dhcp snooping vlan 10,20
```

### enable portfast and bpdu guard on interface

```bash
spanning-tree portfast
spanning-tree bpduguard enable
```

### enable portfast by default

in the config
```bash
spanning-tree portfast default
```

### show port security command

```bash
show port-security
show port-security address
show port-security interface f0/2
```

# routing

## basic router configuration review

### disable dns lookup

```bash
no ip domain-lookup
```

### configure console password, set the session timeout after 6 minutes, enable login and use logging synchronous

```bash
line console 0
password cisco
login
exec-timeout 6 (minutes) 0 (seconds)
logging synchronous
```

