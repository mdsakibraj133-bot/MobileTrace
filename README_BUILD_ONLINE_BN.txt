Mobile Trace - অনলাইনে APK Build করার জন্য

এই ZIP-এ GitHub Actions workflow যোগ করা হয়েছে। আমি এখানে APK নিজে Build/টেস্ট করতে পারিনি, তাই APK প্রস্তুত বলে দাবি করছি না।

কীভাবে ব্যবহার করবেন:
1. ZIP Extract করুন।
2. GitHub-এ একটি নতুন repository বানান।
3. Extract করা ফোল্ডারের ভেতরের সব ফাইল ও .github ফোল্ডারসহ repository-তে upload করুন (ZIP-টি শুধু upload করবেন না)।
4. Commit changes করুন।
5. Repository-তে Actions ট্যাব খুলুন।
6. “Build Mobile Trace APK” workflow বেছে Run workflow চাপুন, অথবা main branch-এ push করলে এটি নিজে চলবে।
7. Build সফল হলে workflow run-এর নিচের Artifacts থেকে MobileTrace-debug-apk ডাউনলোড করুন; ZIP-এর ভেতরে app-debug.apk থাকবে।

নোট:
- প্রথমবার GitHub-এর web UI-তে অনেক ফাইল একসাথে যোগ করা ফোনে অসুবিধা হতে পারে।
- APK debug build হবে; Play Store release signing করা APK নয়।
- অ্যাপটি এখন starter prototype: sample catalogue ও PDF/image external viewer আছে; বাস্তব schematic/boardview database বা automatic line tracing নেই.
