# **Swipe Products App** 📱

A modern Android application with **two screens**:
- **Product Listing Screen** (with search, product images, and a loading indicator)
- **Add Product Screen** (to submit a new product using a `BottomSheetDialogFragment`)

The app follows **MVVM architecture** and uses best practices such as **Retrofit**, **Koin**, and **Coroutines** for API calls. It also supports **offline functionality**, ensuring that newly created products are saved locally and synchronized when the internet is available.

---
## APK
[Apk-release](https://drive.google.com/file/d/1gC_FlgOfgAbBHSUXta3xbuQXV5EiRAhv/view?usp=sharing)

## Screenshots

### Dark-Mode
<img src="https://github.com/user-attachments/assets/0fd4d712-f8e9-478d-ba72-eb94ff6aaf25" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/user-attachments/assets/69ed7b0a-bfde-4c79-b6eb-c44c6fc741fc" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/user-attachments/assets/81a658b1-9385-4619-85aa-461846e96592" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/user-attachments/assets/7768f3bd-4030-4b92-9e3c-c8f2b548e5dd" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/user-attachments/assets/464c0732-94ff-450c-8db7-2f7dc0e9a98b" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/user-attachments/assets/30257316-9752-488c-8757-f6333ee373c2" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/user-attachments/assets/b05f7f64-3a5c-4cc2-b8a0-e338f746e819" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/user-attachments/assets/1d7710c4-c064-44da-9fee-3259a1327769" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/user-attachments/assets/fd5a27dd-3a06-43d7-9e80-4b9fdf807c98" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/user-attachments/assets/92469e61-ed06-464a-b25c-a489e6b33985" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/user-attachments/assets/25f3e77e-e64b-44c9-b0b2-33d39f629573" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/user-attachments/assets/85fbb6b6-aa1a-46fb-8cae-3c10071773f1" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/user-attachments/assets/47cdb2c5-6e03-46d9-a0c0-079c5f4c8352" alt="News App Screenshot 1" width="150" >

### Light-Mode



<img src="https://github.com/user-attachments/assets/487a58a8-fb1f-429b-8605-72f619116506" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/user-attachments/assets/ab9ab19c-2bf0-4a10-9d66-74686815be65" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/user-attachments/assets/ba069e43-2772-4dab-93ac-5b20da22e0d3" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/user-attachments/assets/22a24bf0-7ee7-46db-b8d8-ac14b7de971e" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/user-attachments/assets/45716a72-08bf-4b03-b084-68f9e030396f" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/user-attachments/assets/04df2eed-a829-4cac-b6e2-306ce8122bc8" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/user-attachments/assets/a4f94696-6a7c-4ec4-8319-673c455818c4" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/user-attachments/assets/931af3e1-2c38-4e35-96c5-d932021a8225" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/user-attachments/assets/9ccc2a4e-a135-420d-8a8a-de5fce1914eb" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/user-attachments/assets/23dcdd68-c2de-49fa-8ab1-c56f85d07b05" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/user-attachments/assets/9bfa4fa7-8a07-475e-83b7-20539d540d97" alt="News App Screenshot 1" width="150" >
<img src="https://github.com/user-attachments/assets/f3b0bac4-398d-4c27-8f66-92b5bb0a81f3" alt="News App Screenshot 1" width="150" >


## Video



https://github.com/user-attachments/assets/b21c6683-d9b0-4c27-a595-21cdad4ddd65




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
