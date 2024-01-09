#include <jni.h>
#include "zx.cpp"
#include <string>

//#include "libavfilter/avfilter.h"

extern "C" JNIEXPORT jstring
Java_com_example_audionativec_MainActivity_stringFromJNI(JNIEnv* env, jobject /* this */) {
    zx x;
    std::string hello = "Hello from C++" +x.print();
//    open_input_file();
    return env->NewStringUTF(hello.c_str());
}

//extern "C" JNIEXPORT jstring JNICALL
//Java_com_example_audionativec_MainActivity_audioFromJNI(JNIEnv* env,  jobject /* this */, jstring filePath) {
//
//    const char* file_path = env->GetStringUTFChars(filePath, nullptr);
//
//    // Вызов функции для обработки файла
//    int result = open_input_file(file_path);
//
//    open_input_file();
//    // Освобождение ресурсов
//    avcodec_free_context(&dec_ctx);
//    avformat_close_input(&fmt_ctx);
//
//    // Возврат результата в виде строки
//    return env->NewStringUTF(std::to_string(result).c_str());
//}