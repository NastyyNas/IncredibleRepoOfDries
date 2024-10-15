# Retracted

## Task 2

**Question:** What is the full path of the text file containing the “message”?

**Answer:** C:\Users\Sophie\Desktop\SOPHIE.txt

**Explanation:**
![alt text](image-56.png)

**Question:** What program was used to create the text file?

**Answer:** notepad.exe

**Explanation:**
opening event viewer and follow the path `Application and Service Logs > Microsoft > Windows > Sysmon > Operational.`

![alt text](image-57.png)

![alt text](image-58.png)

![alt text](image-59.png)

![alt text](image-60.png)

**Question:** What is the time of execution of the process that created the text file?

**Answer:** 2024-01-08 14:25:30

**Explanation:**

![alt text](image-61.png)

![alt text](image-62.png)

## Task 3

**Question:** What is the filename of this “installer”?

**Answer:** antivirus.exe

**Explanation:**
![alt text](image-63.png)

**Question:** What is the download location of this installer?

**Answer:** C:\Users\Sophie\download

**Explanation:**

- open the file throw explorer, and you will finid the path.

**Question:** The installer encrypts files and then adds a file extension to the end of the file name. What is this file extension?

**Answer:** .dmp

**Explanation:**

![alt text](image-64.png)

**Question:** The installer reached out to an IP. What is this IP?

**Answer:** 10.10.8.111

**Explanation:**

![alt text](image-65.png)

![alt text](image-66.png)

## Task 4

**Question:** The threat actor logged in via RDP right after the “installer” was downloaded. What is the source IP?

**Answer:** 10.11.27.46

**Explanation:**
![alt text](image-67.png)

**Question:** This other person downloaded a file and ran it. When was this file run? Timezone UTC (Format YYYY-MM-DD hh:mm:ss)

**Answer:** 2024-01-08 14:24:18

**Explanation:**

![alt text](image-68.png)

![alt text](image-69.png)

![alt text](image-70.png)

## Task 5

**Question:** Arrange the following events in sequential order from 1 to 7, based on the timeline in which they occurred.

**Answer:**

1.  Sophie downloaded the malware and ran it.
2.  The malware encrypted the files on the computer and showed a ransomware note.
3.  Sophie ran out and reached out to you for help.
4.  Someone else logged into Sophie’s machine via RDP and started looking around.
5.  The intruder downloaded a decryptor and decrypted all the files.
6.  A note was created on the desktop telling Sophie to check her Bitcoin.
7.  We arrive on the scene to investigate.
