# GroupGroceries App

> Flow Week 3 – Team 3  
>  
> An Android-based group-buying application for groceries, designed for students and single residents.  

---

## A. Team Members
- **Yena Park** – Sookmyung Women’s University, Software Convergence  
- **Jongeun Park** – KAIST, School of Electrical Engineering  
- **Jina Kim** – KAIST, School of Computer Science  

---

## B. Development Environment
- **OS** : Android (minSdk: 21, targetSdk: 31)  
- **Language** : Kotlin, NestJS  
- **IDE** : Android Studio  
- **Target Device** : Samsung Galaxy S10  

---

## C. Application Overview

### 1. Sign Up & Login

![3](https://user-images.githubusercontent.com/76472415/184139124-cbc4609e-f66a-48c5-b039-8aa822b52315.PNG)

**Main Features**
- **Sign Up**  
  - Register via **email & password** with the “SIGN UP” button.  
- **Login**  
  - Access with **email & password** using the “LOGIN” button.  
  - Supports **Kakao social login**.  
  - Automatic login if the user has previously logged in.  

---

### 2. Product List

![4](https://user-images.githubusercontent.com/76472415/184139183-eeb65784-5125-4c7a-89cc-2724f827451b.PNG)

**Main Features**
- **Regional Filtering**  
  - Filter products by selecting **city/district/neighborhood**.  
- **Category Filtering**  
  - Filter products by category type.  
- **Search Bar**  
  - Search for specific products by name.  
  - Provides **autocomplete suggestions** when searching.  

---

### 3. Product Details

![5](https://user-images.githubusercontent.com/76472415/184139236-e9abc6c6-e55c-47b1-ae61-a638bf6888ef.PNG)

**Main Features**
- **Save to Favorites**  
  - Add products to favorites with the **heart button**.  
  - Toggle again to remove from favorites.  
  - View saved items in the **Favorites list**.  
- **Join Group Purchase**  
  - Join a group-buy via the “공구 참여하기” (Join Group Purchase) button and proceed with payment.  
  - When the **minimum number of participants** is reached, the group resets and a new round begins.  
  - View ongoing group-buys in the **Active Participation list**.  

---

### 4. User Information

![6](https://user-images.githubusercontent.com/76472415/184139259-64778529-42e6-4441-91fe-7f8ede65fc78.PNG)

**Main Features**
- **User Profile**  
  - Display profile picture.  
- **Balance**  
  - Check current **point balance**.  
- **Recharge**  
  - Add funds via the **Recharge button**.  
- **Purchase History**  
  - View previously joined group-buys that have already been delivered.  

---

## Installation

1. Initialize the database by sending the following request **once**:  
   ```bash
   GET "http://domain/item/itemCrawl"
2. This request triggers the server to web scrape sample products from the Emart online mall, process them, and store them in the database in a format compatible with this service.
