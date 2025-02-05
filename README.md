# **Swipe Products App** 📱

A modern Android application with **two screens**:
- **Product Listing Screen** (with search, product images, and a loading indicator)
- **Add Product Screen** (to submit a new product using a `BottomSheetDialogFragment`)

The app follows **MVVM architecture** and uses best practices such as **Retrofit**, **Koin**, and **Coroutines** for API calls. It also supports **offline functionality**, ensuring that newly created products are saved locally and synchronized when the internet is available.

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
1️⃣ **Clone the Repository**
```sh
git clone https://github.com/your-repo/swipe-products-app.git
cd swipe-products-app
```
2️⃣ **Open in Android Studio**
Open the project in Android Studio (latest version recommended).
Sync Gradle and ensure dependencies are installed.
3️⃣ **Run the App**
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