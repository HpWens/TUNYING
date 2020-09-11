package com.luck.picture.lib.tools

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.ParcelFileDescriptor
import java.io.*

class FileUtilsToQ {
    companion object {
        
        fun getRealPath(
            context: Context,
            path: String
        ): String? {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                return path
            } else { //安卓10
                var descriptor: ParcelFileDescriptor? = null
                try {
                    descriptor = context.contentResolver
                        .openFile(Uri.parse(path), "r", null)
                    val inputStream =
                        FileInputStream(descriptor!!.fileDescriptor)
                    val byteArray =
                        readBinaryStream(
                            inputStream,
                            descriptor.statSize.toInt()
                        )
                    val split = path.split("/").toTypedArray()
                    if (split.size != 0) {
                        val cachedFile =
                            File(context.cacheDir, split[split.size - 1])
                        val fileSaved =
                            writeFile(
                                cachedFile,
                                byteArray
                            )
                        return if (fileSaved) {
                            cachedFile.absolutePath
                        } else {
                            null
                        }
                    }
                } catch (e: FileNotFoundException) {
                    return null
                } finally {
                    if (descriptor != null) {
                        try {
                            descriptor.close()
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }
            return path
        }

        fun readBinaryStream(
            stream: InputStream,
            byteCount: Int
        ): ByteArray {
            val output = ByteArrayOutputStream()
            try {
                val buffer = ByteArray(if (byteCount > 0) byteCount else 4096)
                var read: Int
                while (stream.read(buffer).also { read = it } >= 0) {
                    output.write(buffer, 0, read)
                }
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                try {
                    stream.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            return output.toByteArray()
        }

        fun writeFile(cachedFile: File, data: ByteArray): Boolean {
            return try {
                var output: BufferedOutputStream? = null
                try {
                    output = BufferedOutputStream(FileOutputStream(cachedFile))
                    output.write(data)
                    output.flush()
                    true
                } finally {
                    output?.close()
                }
            } catch (ex: Exception) {
                false
            }
        }
    }

}
