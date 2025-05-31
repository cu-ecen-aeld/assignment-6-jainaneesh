#ifndef _ACCESS_OK_VERSION_H
#define _ACCESS_OK_VERSION_H

#include <linux/version.h>
#include <linux/uaccess.h>

#if LINUX_VERSION_CODE >= KERNEL_VERSION(5,0,0)
#define ACCESS_OK(addr, size) access_ok(addr, size)
#else
#define ACCESS_OK(addr, size) access_ok(VERIFY_WRITE, addr, size)
#endif
#endif
 
