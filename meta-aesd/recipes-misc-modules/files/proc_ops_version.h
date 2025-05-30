// proc_ops_version.h
#ifndef _PROC_OPS_VERSION_H
#define _PROC_OPS_VERSION_H

#include <linux/version.h>
#include <linux/proc_fs.h>

#if LINUX_VERSION_CODE >= KERNEL_VERSION(5,6,0)
#define PROC_OPS struct proc_ops
#define PROC_OPEN .proc_open
#define PROC_READ .proc_read
#define PROC_LSEEK .proc_lseek
#define PROC_RELEASE .proc_release
#else
#define PROC_OPS struct file_operations
#define PROC_OPEN .open
#define PROC_READ .read
#define PROC_LSEEK .llseak
#define PROC_RELEASE .release
#endif

#endif /* _PROC_OPS_VERSION_H */

