# **Swipe Products App** 📱

A modern Android application with **two screens**:
- **Product Listing Screen** (with search, product images, and a loading indicator)
- **Add Product Screen** (to submit a new product using a `BottomSheetDialogFragment`)

The app follows **MVVM architecture** and uses best practices such as **Retrofit**, **Koin**, and **Coroutines** for API calls. It also supports **offline functionality**, ensuring that newly created products are saved locally and synchronized when the internet is available.

---
## APK
[Apk-release](https://drive.google.com/file/d/1lvVMXCihXlCRE5n7hxXcAPg2HTFYGrek/view?usp=sharing)

## Screenshots

<img src="https://github.com/rockyhappy/Kotlineary/assets/115190222/802da320-b173-4220-99a7-50ae513b267e" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/rockyhappy/Kotlineary/assets/115190222/8581716a-9a83-4921-b59a-7b128be2a080" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/rockyhappy/Kotlineary/assets/115190222/eb1af86a-96d7-42c2-a34b-d659be16f3d6" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/rockyhappy/Kotlineary/assets/115190222/c1626c9a-1571-4349-87b7-a3ab31f781e8" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/rockyhappy/Kotlineary/assets/115190222/ffc02186-5720-490b-9e50-c6b0240b718c" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/rockyhappy/Kotlineary/assets/115190222/1fe6d191-36ea-43fb-8971-4e5030dcaf2c" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/rockyhappy/Kotlineary/assets/115190222/83478117-b7d2-4c39-9c31-cdec155456ce" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/rockyhappy/Kotlineary/assets/115190222/c742ca31-54c9-4f3b-ba6c-599d3315cc4d" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/rockyhappy/Kotlineary/assets/115190222/f74052a5-b807-4cc0-bf79-e3dcc735e475" alt="News App Screenshot 1" width="150" >

## Video


https://github.com/rockyhappy/Kotlineary/assets/115190222/6878b8cf-aa97-4b5e-91a8-d4eae765d88d
---

## **📌 Features**
### **📜 Product Listing Screen (Fragment)**
✅ Display a list of products using **RecyclerView**.  
✅ **Search functionality** to filter products.  
✅ **Collapsing Toolbar** using **CoordinatorLayout** for a modern UI.  
✅ **Shimmer Effect** for loading placeholders.  
✅ **Swipe to Refresh** using `SwipeRefreshLayout`.  
✅ Load **product images from URLs** (default image used if empty).  
✅ **Floating Action Button (FAB)** to navigate to the Add Product screen.

---

### **➕ Add Product Screen (BottomSheetDialogFragment)**
✅ Enter **Product Name, Selling Price, and Tax Rate** in text fields.  
✅ Select **Product Type** from a dropdown.  
✅ **Image Selection** (JPEG/PNG, 1:1 ratio).  
✅ **Form Validation** (Required fields, decimal input validation).  
✅ **Progress Indicator** while submitting.  
✅ **POST API Request** (`multipart/form-data`).  
✅ **Success Dialog & Notification** after successful submission.  
✅ **Offline Storage** for unsent products (syncs when internet is available).

---

## **🛠 Tech Stack & Libraries**
- **MVVM Architecture** 🏗
- **Retrofit** – For API calls.
- **OkHttp & Chucker** – Network debugging.
- **Koin** – Dependency Injection.
- **Coroutines & Flow** – Async operations.
- **Room Database** – Offline caching.
- **Navigation Component** – Fragment navigation.
- **Shimmer Effect** – Loading animations.
- **SwipeRefreshLayout** – Pull to refresh.
- **Glide** – Image loading.
- **WorkManager** – Background syncing of offline data.

---

## **🌐 API Endpoints**
### **📥 Fetch Products**
**GET** `https://app.getswipe.in/api/public/get`  
📌 **Response:**
```json
[
    {
        "image": "https://image-url.jpg",
        "price": 1694.91,
        "product_name": "Sample Product",
        "product_type": "Product",
        "tax": 18.0
    }
]
```
---
## **Project Structure**
```
📦 SwipeProductsApp
├─── 📂 data
│   ├─── 📂 local
│   │   ├─── 📂 databases
│   │   └─── 📂 services
│   ├─── 📂 models
│   ├─── 📂 remote
│   │   ├─── 📂 dto
│   │   └─── 📂 services
│   └─── 📂 repository
│       ├─── 📂 local
│       └─── 📂 remote
├─── 📂 di
├─── 📂 domain
│   ├─── 📂 models
│   ├─── 📂 repository
│   │   ├─── 📂 local
│   │   └─── 📂 remote
│   ├─── 📂 sharedModels
│   ├─── 📂 usecases
│   └─── 📂 workers
├─── 📂 presentation
│   ├─── 📂 adapters
│   └─── 📂 screens
│       ├─── 📂 addProductScreen
│       └─── 📂 homeScreen
└─── 📂 utility
 ```
---
## **📲 How to Run the App**
- 1️⃣ **Clone the Repository**
```sh
git clone https://github.com/rockyhappy/Assignment.git
cd Assignment
```
- 2️⃣ **Open in Android Studio**
Open the project in Android Studio (latest version recommended).
Sync Gradle and ensure dependencies are installed.
- 3️⃣ **Run the App**
Use an Android Emulator or a Physical Device.
Make sure Internet is enabled for API calls.

---
## **📝 Additional Notes**
### **Offline Support Implementation** 📴
- If no internet, products added via the Add Product screen are stored in **Room DB**.
- When internet is available, **WorkManager** syncs data in the background.

### **Error Handling** ⚠
- **Network errors** show a **Snackbar**.
- **API failures** return a proper **Toast** message.
- **Input validation** prevents incorrect data submission.