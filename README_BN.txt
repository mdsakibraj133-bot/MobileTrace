Mobile Trace Android project (starter)
======================================

এই প্রজেক্টে আছে:
- ফোনের ব্র্যান্ড/মডেল/ডায়াগ্রাম টাইপ সার্চ করার ডেমো ক্যাটালগ
- PDF বা ছবি বেছে নিয়ে বাহ্যিক viewer-এ খোলার ব্যবস্থা
- Android Studio/AndroidIDE বা GitHub Actions দিয়ে APK build করার project structure

সীমাবদ্ধতা:
- এখানে বাস্তব সার্কিট schematic, boardview, IC pinout বা tracing data নেই।
- ক্যাটালগের মডেলগুলো নমুনা; এগুলোকে আসল ডায়াগ্রাম মনে করবেন না।
- এই প্যাকেজে gradle-wrapper.jar / gradlew নেই। AndroidIDE-তে installed Gradle দিয়ে project sync/build করুন, অথবা GitHub Actions workflow যোগ করুন।
- APK এখানে build/test করা হয়নি; তাই build সফল হবে বলে নিশ্চিত দাবি করা হচ্ছে না।

AndroidIDE:
1. ZIP extract করুন।
2. AndroidIDE থেকে extracted folder-টি Open Project করুন।
3. Gradle sync শেষ হতে দিন।
4. Build/Run করুন। Error এলে পুরো error message-এর screenshot দিন।
