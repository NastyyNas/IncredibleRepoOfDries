# systemd
### what is systemd?
 - open-source software that provides system components for linux
 - main goal: unify service config and behavior across linux distro's
 - provides a system and service manager that runs as PID1 and starts the rest of the system
 - provices replacements for various daemons and utilities, including device management, login management, network connection management, and event logging.

### what does systemd do?
takes care of boot and all types of services

### practical targets
 - graphical.target
 - multi-user.target
 - rescue.target
 - reboot.target

# Booting Linux
## legacy BIOS
### begrippen
- POST: power-on self-test
- UEFI: Unified Firmware Interface
- MBR: Master Boot Record

UEFI nieuwere versie van BIOS. Heeft dezelfde functie.

![BIOS vs UEFI](Images/BIOSvsUEFI.png)

technical advantages of using UEFI:
- Secure Boot
- GUID Partition Table (GPT)
- Platform and architecture Independence
- Consistend Variables and Services
- Modular and Extensible
- Improved Boot Performance

## UEFI: Enhancing Bootloader Functionality and Flexibility

![ArchitectureUEFI](Images/ArchitectureUEFI.png)
 