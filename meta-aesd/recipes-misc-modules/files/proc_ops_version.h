// proc_ops_version.h
#ifndef _PROC_OPS_VERSION_H
#define _PROC_OPS_VERSION_H

#include <linux/version.h>
#include <linux/proc_fs.h>

#if LINUX_VERSION_CODE >= KERNEL_VERSION(5,6,0)
#define PROC_OPS struct proc_ops
//#define PROC_READ_FUNC .proc_read
//#define PROC_WRITE_FUNC .proc_write
#else
#define PROC_OPS struct file_operations
//#define PROC_READ_FUNC .read
//#define PROC_WRITE_FUNC .write
#endif

#endif /* _PROC_OPS_VERSION_H */

