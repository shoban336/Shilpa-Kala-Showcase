SHILPA-KALA SHOWCASE
Standard Operating Procedure (SOP)
Complete Development Guide for Android Studio

MASTER SOP DOCUMENT
Version: 1.0 | Platform: Android Studio | Language: Kotlin | Year: 2025


PART 1: PROJECT OVERVIEW & VISION

1.1 What Is This App?
Shilpa-Kala Showcase is a premium dual-sided marketplace Android application built in Kotlin. It digitally connects traditional stone and wood carvers (called Shilpis) from Shivarapatna with national and international buyers. The app preserves artisanal heritage by providing verified digital portfolios, work-in-progress documentation, high-resolution galleries, and direct WhatsApp-based negotiation.
1.2 The Problem This App Solves
ProblemImpactShilpis have zero digital presenceWorld-class work is invisible globallyBuyers depend on middlemenArtisans lose 30–50% of actual valueRare techniques are undocumentedHoysala, Dravidian styles are dyingNo authentication system existsFake sellers damage trust1.3 Core App Philosophy
"Every stone has a story. Every carver deserves a stage."
The app must feel premium, cultural, and trustworthy. It is NOT a cheap e-commerce app. It is a digital heritage gallery that also enables commerce.
1.4 Two Types of Users
USER TYPE 1 — SELLER (Shilpi / Artisan / Vendor)
• Stone carvers, wood carvers, heritage craftspeople
• They upload products, manage portfolios, document work-in-progress
• They receive buyer inquiries via WhatsApp
USER TYPE 2 — BUYER (Customer / Collector / Patron)
• Art collectors, temple trusts, interior designers, international buyers
• They browse galleries, save items, leave reviews, initiate WhatsApp contact

PART 2: TECHNICAL SPECIFICATIONS

2.1 Development Environment
text
IDE:                    Android Studio Hedgehog (2023.1.1) or newer
Language:               Kotlin (100%) — Primary language
Min SDK:                API 26 (Android 8.0 Oreo)
Target SDK:             API 34 (Android 14)
Compile SDK:            API 34
Build Tool:             Gradle 8.x with Kotlin DSL
Architecture Pattern:   MVVM (Model-View-ViewModel)
2.2 Complete Gradle Dependencies (build.gradle.kts — App Level)
Kotlin
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.shilpakala.showcase"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.shilpakala.showcase"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // Core Android
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.6")

    // ViewModel + LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    // Retrofit + OkHttp (Networking)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Glide (Image Loading)
    implementation("com.github.bumptech.glide:glide:4.16.0")
    kapt("com.github.bumptech.glide:compiler:4.16.0")

    // PhotoView (Pinch to Zoom)
    implementation("com.github.chrisbanes:PhotoView:2.3.0")

    // Room Database (Local Bookmarks)
    implementation("androidx.room:room-runtime:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")

    // Firebase (Authentication + Firestore + Storage)
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation("com.google.firebase:firebase-messaging-ktx")

    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // CardView
    implementation("androidx.cardview:cardview:1.0.0")

    // SwipeRefreshLayout
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    // Lottie Animations
    implementation("com.airbnb.android:lottie:6.3.0")

    // Shimmer Loading Effect
    implementation("com.facebook.shimmer:shimmer:0.5.0")

    // MPAndroidChart (for seller analytics)
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")

    // CircleImageView
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // Rating Bar
    implementation("com.github.ome450901:SimpleRatingBar:1.5.1")

    // DataStore Preferences
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // SDP & SSP (Responsive Dimensions)
    implementation("com.intuit.sdp:sdp-android:1.1.0")
    implementation("com.intuit.ssp:ssp-android:1.1.0")
}
2.3 Project Folder Structure
text
com.shilpakala.showcase/
│
├── data/
│   ├── local/
│   │   ├── database/
│   │   │   ├── ShilpaKalaDatabase.kt
│   │   │   ├── dao/
│   │   │   │   ├── BookmarkDao.kt
│   │   │   │   └── CacheDao.kt
│   │   │   └── entities/
│   │   │       └── BookmarkEntity.kt
│   │   └── preferences/
│   │       └── UserPreferences.kt
│   │
│   ├── remote/
│   │   ├── api/
│   │   │   ├── ApiService.kt
│   │   │   └── FirebaseService.kt
│   │   ├── models/
│   │   │   ├── User.kt
│   │   │   ├── Product.kt
│   │   │   ├── Seller.kt
│   │   │   ├── Review.kt
│   │   │   ├── TimelineEntry.kt
│   │   │   └── HeritageStory.kt
│   │   └── interceptors/
│   │       └── OfflineCacheInterceptor.kt
│   │
│   └── repository/
│       ├── AuthRepository.kt
│       ├── ProductRepository.kt
│       ├── SellerRepository.kt
│       └── ReviewRepository.kt
│
├── ui/
│   ├── splash/
│   │   └── SplashActivity.kt
│   ├── onboarding/
│   │   └── OnboardingActivity.kt
│   ├── auth/
│   │   ├── RoleSelectionActivity.kt
│   │   ├── PhoneAuthActivity.kt
│   │   └── OtpVerificationActivity.kt
│   ├── buyer/
│   │   ├── BuyerMainActivity.kt
│   │   ├── home/
│   │   │   ├── BuyerHomeFragment.kt
│   │   │   └── BuyerHomeViewModel.kt
│   │   ├── gallery/
│   │   │   ├── GalleryFragment.kt
│   │   │   └── GalleryViewModel.kt
│   │   ├── detail/
│   │   │   ├── ProductDetailActivity.kt
│   │   │   └── ProductDetailViewModel.kt
│   │   ├── fullscreen/
│   │   │   └── FullScreenImageActivity.kt
│   │   ├── timeline/
│   │   │   └── WorkInProgressActivity.kt
│   │   ├── heritage/
│   │   │   └── HeritageStoryActivity.kt
│   │   ├── search/
│   │   │   ├── SearchFragment.kt
│   │   │   └── SearchViewModel.kt
│   │   ├── saved/
│   │   │   ├── SavedFragment.kt
│   │   │   └── SavedViewModel.kt
│   │   └── profile/
│   │       ├── BuyerProfileFragment.kt
│   │       └── BuyerProfileViewModel.kt
│   │
│   ├── seller/
│   │   ├── SellerMainActivity.kt
│   │   ├── dashboard/
│   │   │   ├── SellerDashboardFragment.kt
│   │   │   └── SellerDashboardViewModel.kt
│   │   ├── portfolio/
│   │   │   ├── PortfolioFragment.kt
│   │   │   └── PortfolioViewModel.kt
│   │   ├── addproduct/
│   │   │   ├── AddProductActivity.kt
│   │   │   └── AddProductViewModel.kt
│   │   ├── addtimeline/
│   │   │   ├── AddTimelineActivity.kt
│   │   │   └── AddTimelineViewModel.kt
│   │   └── sellerprofile/
│   │       ├── SellerProfileFragment.kt
│   │       └── SellerProfileViewModel.kt
│   │
│   └── shared/
│       ├── adapters/
│       │   ├── ProductGridAdapter.kt
│       │   ├── TimelineAdapter.kt
│       │   ├── ReviewAdapter.kt
│       │   └── HeritageAdapter.kt
│       └── components/
│           ├── ShimmerLoadingView.kt
│           └── CustomRatingBar.kt
│
├── utils/
│   ├── WhatsAppHelper.kt
│   ├── GlideHelper.kt
│   ├── OtpHelper.kt
│   ├── NetworkUtils.kt
│   ├── Constants.kt
│   └── Extensions.kt
│
└── ShilpaKalaApplication.kt

PART 3: DATA MODELS

3.1 Firebase Firestore Collections Structure
text
Firestore Root/
├── users/
│   └── {userId}/
│       ├── uid: String
│       ├── phone: String
│       ├── name: String
│       ├── role: String ("buyer" | "seller")
│       ├── profileImageUrl: String
│       ├── isVerified: Boolean
│       ├── createdAt: Timestamp
│       └── fcmToken: String
│
├── sellers/
│   └── {sellerId}/
│       ├── uid: String
│       ├── name: String
│       ├── phone: String
│       ├── village: String
│       ├── specialty: String
│       ├── bio: String
│       ├── profileImageUrl: String
│       ├── isVerifiedArtisan: Boolean
│       ├── rating: Float
│       ├── totalReviews: Int
│       ├── whatsappNumber: String
│       ├── yearsOfExperience: Int
│       ├── carvingStyles: List<String>
│       └── joinedDate: Timestamp
│
├── products/
│   └── {productId}/
│       ├── productId: String
│       ├── sellerId: String
│       ├── title: String
│       ├── description: String
│       ├── material: String ("Black Granite" | "Sandstone" | "Marble" | "Wood")
│       ├── carvingStyle: String ("Hoysala" | "Dravidian" | "Chola" | "Modern")
│       ├── price: Long
│       ├── priceUnit: String ("INR")
│       ├── availability: String ("Available" | "Sold" | "On Order")
│       ├── stoneFreshness: String ("Fresh" | "Aged" | "Antique Finish")
│       ├── dimensions: String
│       ├── weight: String
│       ├── primaryImageUrl: String
│       ├── allImageUrls: List<String>
│       ├── rating: Float
│       ├── totalReviews: Int
│       ├── viewCount: Int
│       ├── isActive: Boolean
│       └── createdAt: Timestamp
│
├── timeline/
│   └── {productId}/
│       └── entries/
│           └── {entryId}/
│               ├── entryId: String
│               ├── productId: String
│               ├── stage: String
│               ├── description: String
│               ├── imageUrl: String
│               ├── stageNumber: Int
│               └── timestamp: Timestamp
│
├── reviews/
│   └── {productId}/
│       └── entries/
│           └── {reviewId}/
│               ├── reviewId: String
│               ├── buyerId: String
│               ├── buyerName: String
│               ├── buyerImageUrl: String
│               ├── rating: Float
│               ├── comment: String
│               ├── isVerifiedPurchase: Boolean
│               └── createdAt: Timestamp
│
└── heritage_stories/
    └── {storyId}/
        ├── storyId: String
        ├── title: String
        ├── style: String
        ├── description: String
        ├── history: String
        ├── shilpiQuote: String
        ├── coverImageUrl: String
        └── relatedProductIds: List<String>
3.2 Kotlin Data Models
Kotlin
// User.kt
data class User(
    val uid: String = "",
    val phone: String = "",
    val name: String = "",
    val role: String = "",
    val profileImageUrl: String = "",
    val isVerified: Boolean = false,
    val createdAt: Timestamp? = null,
    val fcmToken: String = ""
)

// Product.kt
data class Product(
    val productId: String = "",
    val sellerId: String = "",
    val title: String = "",
    val description: String = "",
    val material: String = "",
    val carvingStyle: String = "",
    val price: Long = 0,
    val priceUnit: String = "INR",
    val availability: String = "Available",
    val stoneFreshness: String = "",
    val dimensions: String = "",
    val weight: String = "",
    val primaryImageUrl: String = "",
    val allImageUrls: List<String> = emptyList(),
    val rating: Float = 0f,
    val totalReviews: Int = 0,
    val viewCount: Int = 0,
    val isActive: Boolean = true,
    val createdAt: Timestamp? = null
)

// Seller.kt
data class Seller(
    val uid: String = "",
    val name: String = "",
    val phone: String = "",
    val village: String = "",
    val specialty: String = "",
    val bio: String = "",
    val profileImageUrl: String = "",
    val isVerifiedArtisan: Boolean = false,
    val rating: Float = 0f,
    val totalReviews: Int = 0,
    val whatsappNumber: String = "",
    val yearsOfExperience: Int = 0,
    val carvingStyles: List<String> = emptyList(),
    val joinedDate: Timestamp? = null
)

// TimelineEntry.kt
data class TimelineEntry(
    val entryId: String = "",
    val productId: String = "",
    val stage: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val stageNumber: Int = 0,
    val timestamp: Timestamp? = null
)

// Review.kt
data class Review(
    val reviewId: String = "",
    val buyerId: String = "",
    val buyerName: String = "",
    val buyerImageUrl: String = "",
    val rating: Float = 0f,
    val comment: String = "",
    val isVerifiedPurchase: Boolean = false,
    val createdAt: Timestamp? = null
)

// HeritageStory.kt
data class HeritageStory(
    val storyId: String = "",
    val title: String = "",
    val style: String = "",
    val description: String = "",
    val history: String = "",
    val shilpiQuote: String = "",
    val coverImageUrl: String = "",
    val relatedProductIds: List<String> = emptyList()
)

// BookmarkEntity.kt (Room DB)
@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey val productId: String,
    val title: String,
    val primaryImageUrl: String,
    val price: Long,
    val material: String,
    val savedAt: Long = System.currentTimeMillis()
)

PART 4: COMPLETE SCREEN-BY-SCREEN SPECIFICATION

SCREEN 1: SPLASH SCREEN
Screen ID: SCR-001
File: SplashActivity.kt + activity_splash.xml

PURPOSE:
First screen users see when opening the app. Shows the brand identity, loads essential data, and decides where to route the user.
UI LAYOUT SPECIFICATION:
text
Background: Full screen gradient
    - Top color: #3E2723 (Deep Brown — Stone color)
    - Bottom color: #1A0F0A (Near Black)

Center Logo Area:
    - App Logo Image: 120dp × 120dp (centered)
    - App Name Text: "Shilpa-Kala Showcase"
        Font: Custom heritage font OR Serif Bold
        Size: 28sp
        Color: #D4A843 (Heritage Gold)
    - Tagline Text: "Preserving India's Stone Carving Heritage"
        Font: Regular
        Size: 14sp
        Color: #FFFFFF with 70% opacity

Bottom Area:
    - Lottie Animation: Circular loader in gold color
    - Version text: "v1.0" — small, 10sp, bottom-right corner
    - "Made in India" small text bottom-center
LOGIC / BACKEND:
Kotlin
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Make it full screen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        lifecycleScope.launch {
            delay(2500) // Show splash for 2.5 seconds
            checkUserSession()
        }
    }

    private fun checkUserSession() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        
        if (currentUser != null) {
            // User is logged in — check their role
            val role = getUserRoleFromPreferences()
            when (role) {
                "buyer" -> navigateTo(BuyerMainActivity::class.java)
                "seller" -> navigateTo(SellerMainActivity::class.java)
                else -> navigateTo(RoleSelectionActivity::class.java)
            }
        } else {
            // First time user — show onboarding
            val hasSeenOnboarding = getOnboardingStatus()
            if (hasSeenOnboarding) {
                navigateTo(RoleSelectionActivity::class.java)
            } else {
                navigateTo(OnboardingActivity::class.java)
            }
        }
        finish() // Remove splash from back stack
    }
}
NAVIGATION FROM THIS SCREEN:
• → OnboardingActivity (first time users)
• → RoleSelectionActivity (returning users, not logged in)
• → BuyerMainActivity (logged-in buyers)
• → SellerMainActivity (logged-in sellers)

SCREEN 2: ONBOARDING SCREEN
Screen ID: SCR-002
File: OnboardingActivity.kt + activity_onboarding.xml

PURPOSE:
3-slide carousel introducing the app to first-time users.
UI LAYOUT SPECIFICATION:
text
Layout: ViewPager2 with full-screen slides

Slide 1:
    Image: Heritage stone carving illustration
    Title: "Discover Ancient Indian Crafts"
    Subtitle: "Explore handcrafted sculptures from master Shilpis of Shivarapatna"
    Color Accent: #D4A843 (Gold)

Slide 2:
    Image: Artisan working on stone carving
    Title: "Work in Progress — Witnessed Live"
    Subtitle: "Follow the journey from raw stone block to breathtaking sculpture"
    Color Accent: #5D4037 (Brown)

Slide 3:
    Image: WhatsApp chat icon with sculpture
    Title: "Negotiate Directly with the Shilpi"
    Subtitle: "One tap connects you via WhatsApp with the exact Product ID — no middlemen"
    Color Accent: #2E7D32 (Green — WhatsApp color)

Bottom Controls:
    - Dot indicators (3 dots, active dot larger)
    - "Next" button (right side) — moves to next slide
    - "Skip" text button (top-right) — skip all slides
    - On last slide: "Next" becomes "Get Started" button
    
Footer:
    - "Already have an account? Login" text (clickable)
LOGIC:
Kotlin
class OnboardingActivity : AppCompatActivity() {

    private val slides = listOf(
        OnboardingSlide(
            R.drawable.ic_onboard_1,
            "Discover Ancient Indian Crafts",
            "Explore handcrafted sculptures from master Shilpis of Shivarapatna"
        ),
        OnboardingSlide(
            R.drawable.ic_onboard_2,
            "Work in Progress — Witnessed Live",
            "Follow the journey from raw stone block to breathtaking sculpture"
        ),
        OnboardingSlide(
            R.drawable.ic_onboard_3,
            "Negotiate Directly with the Shilpi",
            "One tap connects you via WhatsApp with the exact Product ID"
        )
    )

    private fun finishOnboarding() {
        // Save that user has seen onboarding
        saveOnboardingComplete()
        startActivity(Intent(this, RoleSelectionActivity::class.java))
        finish()
    }
}
NAVIGATION FROM THIS SCREEN:
• → RoleSelectionActivity (after completing or skipping)

SCREEN 3: ROLE SELECTION SCREEN
Screen ID: SCR-003
File: RoleSelectionActivity.kt + activity_role_selection.xml

PURPOSE:
User selects whether they are a Buyer or Seller before authentication.
UI LAYOUT SPECIFICATION:
text
Background: Heritage texture background (#FDF8F0 — Warm cream)

Top Section:
    - App logo (smaller, 60dp)
    - Text: "Welcome to Shilpa-Kala"
        Size: 24sp, Bold, Color: #3E2723
    - Subtext: "Tell us who you are"
        Size: 14sp, Color: #757575

Center Section (Two Large Cards):

    Card 1 — BUYER:
        Background: #3E2723 (Dark Brown)
        Icon: Shopping bag / eye icon (white, 48dp)
        Title: "I am a Buyer"
        Subtitle: "Explore & purchase authentic stone art"
        Size: Full width, Height: 160dp
        Corner Radius: 16dp
        Selected State: Gold border (#D4A843), slight scale up

    Card 2 — SELLER (Shilpi):
        Background: #D4A843 (Heritage Gold)
        Icon: Hammer / chisel icon (dark, 48dp)
        Title: "I am a Shilpi"
        Subtitle: "Showcase your craft to the world"
        Size: Full width, Height: 160dp
        Corner Radius: 16dp
        Selected State: Dark border (#3E2723), slight scale up

Bottom Section:
    - "Continue" button (disabled until role selected)
        Active: Background #D4A843, Text #3E2723, Bold
        Disabled: Background #BDBDBD, Text #FFFFFF

Footer:
    - "By continuing you agree to our Terms & Privacy Policy"
        Clickable links for Terms and Privacy
LOGIC:
Kotlin
class RoleSelectionActivity : AppCompatActivity() {

    private var selectedRole: String = ""

    private fun onRoleSelected(role: String) {
        selectedRole = role
        // Update UI — highlight selected card
        updateCardSelection(role)
        // Enable continue button
        binding.btnContinue.isEnabled = true
    }

    private fun onContinueClicked() {
        val intent = Intent(this, PhoneAuthActivity::class.java)
        intent.putExtra("USER_ROLE", selectedRole)
        startActivity(intent)
    }
}
NAVIGATION FROM THIS SCREEN:
• → PhoneAuthActivity (with "USER_ROLE" extra = "buyer" or "seller")

SCREEN 4: PHONE NUMBER AUTHENTICATION SCREEN
Screen ID: SCR-004
File: PhoneAuthActivity.kt + activity_phone_auth.xml

PURPOSE:
User enters their phone number for OTP-based authentication.
UI LAYOUT SPECIFICATION:
text
Background: White / #FAFAFA

Top Section:
    - Back arrow (top-left)
    - Heritage decorative line / divider
    - Icon: Phone icon in gold circle (72dp)
    - Title: "Enter Your Phone Number"
        Size: 22sp, Bold, Color: #3E2723
    - Subtitle: "We'll send you a verification code"
        Size: 14sp, Color: #757575

Phone Input Section:
    - Country Code Selector:
        Default: 🇮🇳 +91
        Shows flag + code
        Tappable — opens country picker dialog
    - Phone Number Field:
        Hint: "10-digit mobile number"
        Input Type: numberPhone
        Max Length: 10
        Font Size: 18sp
        Underline color: #D4A843 when active
    - Full border around country code + number field combined

Error States:
    - Empty field: "Please enter your phone number"
    - Invalid number: "Please enter a valid 10-digit number"
    - Error text: Red, 12sp, below field

Send OTP Button:
    - Text: "Send OTP"
    - Background: #3E2723
    - Text Color: White
    - Corner Radius: 8dp
    - Full width
    - Shows loading spinner when pressed

Bottom:
    - Role indicator: "Joining as: BUYER" or "Joining as: SHILPI"
        Pill-shaped badge with appropriate color
LOGIC:
Kotlin
class PhoneAuthActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private var userRole: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userRole = intent.getStringExtra("USER_ROLE") ?: "buyer"
        auth = FirebaseAuth.getInstance()
    }

    private fun sendOtp(phoneNumber: String) {
        val formattedNumber = "+91$phoneNumber"
        
        showLoading(true)

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(formattedNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(verificationCallbacks)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private val verificationCallbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // Auto-verification on some devices
            signInWithCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            showLoading(false)
            showError(e.message ?: "Verification failed")
        }

        override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
            showLoading(false)
            // Navigate to OTP screen
            val intent = Intent(this@PhoneAuthActivity, OtpVerificationActivity::class.java)
            intent.putExtra("VERIFICATION_ID", verificationId)
            intent.putExtra("PHONE_NUMBER", phoneNumber)
            intent.putExtra("USER_ROLE", userRole)
            startActivity(intent)
        }
    }
}
NAVIGATION FROM THIS SCREEN:
• → OtpVerificationActivity (after sending OTP)
• ← Back to RoleSelectionActivity

SCREEN 5: OTP VERIFICATION SCREEN
Screen ID: SCR-005
File: OtpVerificationActivity.kt + activity_otp_verification.xml

PURPOSE:
User enters the 6-digit OTP received on their phone to verify identity.
UI LAYOUT SPECIFICATION:
text
Background: White

Top Section:
    - Back arrow
    - Lock icon in gold circle (72dp)
    - Title: "Verify Your Number"
        Size: 22sp, Bold
    - Subtitle: "Enter the 6-digit code sent to +91-XXXXXXXXXX"
        Size: 14sp, Color: #757575
        Phone number in BOLD within subtitle

OTP Input Section:
    6 Individual Box Fields:
        - Each box: 48dp × 56dp
        - Border: 1dp, Color: #BDBDBD (inactive), #D4A843 (active), #4CAF50 (filled)
        - Corner Radius: 8dp
        - Center-aligned digits
        - Font Size: 22sp, Bold
        - Spacing between boxes: 8dp
        - Auto-advance to next box on digit entry
        - Auto-backspace to previous box on delete

Timer Section:
    - "Resend OTP in: 00:45" (countdown timer)
    - When timer hits 0: "Resend OTP" becomes tappable
    - Resend link: Color #D4A843, Underlined

Verify Button:
    - Text: "Verify & Continue"
    - Background: #3E2723 (enabled) / #BDBDBD (disabled)
    - Disabled until all 6 digits entered
    - Shows loading spinner on tap

Error Section:
    - "Invalid OTP. Please try again." — Red text
    - "OTP expired. Please request a new one." — Red text

Success Animation:
    - Lottie checkmark animation plays before navigation
LOGIC:
Kotlin
class OtpVerificationActivity : AppCompatActivity() {

    private var verificationId: String = ""
    private var userRole: String = ""
    private var phoneNumber: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verificationId = intent.getStringExtra("VERIFICATION_ID") ?: ""
        userRole = intent.getStringExtra("USER_ROLE") ?: "buyer"
        phoneNumber = intent.getStringExtra("PHONE_NUMBER") ?: ""
    }

    private fun verifyOtp(otp: String) {
        showLoading(true)
        val credential = PhoneAuthProvider.getCredential(verificationId, otp)
        
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = task.result?.user
                    user?.let { createOrFetchUserProfile(it.uid) }
                } else {
                    showLoading(false)
                    showError("Invalid OTP. Please try again.")
                }
            }
    }

    private fun createOrFetchUserProfile(uid: String) {
        val db = FirebaseFirestore.getInstance()
        
        db.collection("users").document(uid).get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    // Existing user — fetch role and navigate
                    val role = document.getString("role") ?: userRole
                    saveUserSession(uid, role)
                    navigateToMain(role)
                } else {
                    // New user — create profile
                    val newUser = User(
                        uid = uid,
                        phone = phoneNumber,
                        role = userRole,
                        createdAt = Timestamp.now()
                    )
                    db.collection("users").document(uid).set(newUser)
                        .addOnSuccessListener {
                            if (userRole == "seller") {
                                // Create seller profile too
                                createSellerProfile(uid)
                            }
                            saveUserSession(uid, userRole)
                            navigateToMain(userRole)
                        }
                }
            }
    }

    private fun navigateToMain(role: String) {
        showSuccessAnimation()
        val intent = when (role) {
            "seller" -> Intent(this, SellerMainActivity::class.java)
            else -> Intent(this, BuyerMainActivity::class.java)
        }
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}
NAVIGATION FROM THIS SCREEN:
• → BuyerMainActivity (if role = buyer) — CLEARS BACK STACK
• → SellerMainActivity (if role = seller) — CLEARS BACK STACK
• ← Back to PhoneAuthActivity

SCREEN 6: BUYER MAIN ACTIVITY (HOST)
Screen ID: SCR-006
File: BuyerMainActivity.kt + activity_buyer_main.xml

PURPOSE:
Container activity for all buyer screens. Hosts bottom navigation.
UI LAYOUT SPECIFICATION:
text
Layout: Coordinator Layout with Bottom Navigation

Bottom Navigation Bar:
    5 tabs:
    1. Home (house icon) — "Home"
    2. Gallery (grid icon) — "Gallery"
    3. Search (magnify icon) — "Search"
    4. Saved (heart/bookmark icon) — "Saved"
    5. Profile (person icon) — "Profile"

    Active Tab Color: #D4A843 (Gold)
    Inactive Tab Color: #9E9E9E (Grey)
    Background: White
    Elevation: 8dp

Top App Bar (shown on Home & Gallery):
    - App name/logo (left)
    - Notification bell icon (right)
    - Language toggle (KA/EN) (right)

Fragment Container:
    - Takes up rest of screen above bottom nav
NAVIGATION:
• Tab 1 Home → BuyerHomeFragment
• Tab 2 Gallery → GalleryFragment
• Tab 3 Search → SearchFragment
• Tab 4 Saved → SavedFragment
• Tab 5 Profile → BuyerProfileFragment

SCREEN 7: BUYER HOME SCREEN
Screen ID: SCR-007
File: BuyerHomeFragment.kt + fragment_buyer_home.xml

PURPOSE:
Main landing screen for buyers. Shows featured sculptures, categories, and top sellers.
UI LAYOUT SPECIFICATION:
text
Overall Layout: NestedScrollView containing multiple sections

SECTION 1 — Header Banner:
    Height: 200dp
    ViewPager2 with auto-scroll (3 second interval)
    Featured products as hero images (high-res, loaded via Glide)
    Dot indicators at bottom
    Overlay gradient (bottom-to-transparent dark)
    Text on image: Product name + Starting price

SECTION 2 — "Browse by Style" (Horizontal RecyclerView):
    Title: "Browse by Carving Style" (18sp, Bold, #3E2723)
    ScrollDirection: Horizontal
    Cards (each):
        - Size: 100dp × 120dp
        - Image: Style illustration
        - Text: "Hoysala", "Dravidian", "Chola", "Modern", "Wood"
        - Corner Radius: 12dp
        - Background: Gradient based on style
    Tap → filters gallery by that style

SECTION 3 — "Featured Sculptures" (Grid):
    Title: "Featured Sculptures" with "See All" link
    Grid: 2 columns
    Each Card:
        - Image: 180dp height, Glide loaded, centerCrop
        - Shimmer placeholder while loading
        - Title: Product name (14sp, Bold)
        - Price: "₹X,XXX" (12sp, Gold color)
        - Rating: Star icons + number
        - "Stone" tag: Freshness indicator
        - Availability chip: "Available" (green) / "Sold" (red)
        - Corner Radius: 12dp
        - Elevation: 4dp

SECTION 4 — "Verified Shilpis" (Horizontal scroll):
    Title: "Our Master Artisans"
    CircleImageView (70dp) + Name + Village
    Blue verified badge overlaid on image
    Tap → opens SellerPortfolioActivity

SECTION 5 — "Heritage Stories" (Horizontal scroll):
    Title: "Heritage & History"
    Card: Wide image + Title + "5 min read"
    Tap → HeritageStoryActivity

Loading State:
    Shimmer effect on all sections while data loads
VIEWMODEL LOGIC:
Kotlin
class BuyerHomeViewModel : ViewModel() {

    private val productRepo = ProductRepository()
    private val sellerRepo = SellerRepository()

    val featuredProducts = MutableLiveData<List<Product>>()
    val topSellers = MutableLiveData<List<Seller>>()
    val heritageStories = MutableLiveData<List<HeritageStory>>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        loadHomeData()
    }

    private fun loadHomeData() {
        viewModelScope.launch {
            isLoading.value = true
            try {
                // Load all sections in parallel
                val productsDeferred = async { productRepo.getFeaturedProducts() }
                val sellersDeferred = async { sellerRepo.getTopSellers() }
                val storiesDeferred = async { productRepo.getHeritageStories() }

                featuredProducts.value = productsDeferred.await()
                topSellers.value = sellersDeferred.await()
                heritageStories.value = storiesDeferred.await()
            } catch (e: Exception) {
                // Handle error
            } finally {
                isLoading.value = false
            }
        }
    }
}
NAVIGATION FROM THIS SCREEN:
• Tap product card → ProductDetailActivity
• Tap "See All" → GalleryFragment (with navigation)
• Tap seller card → SellerPortfolioActivity
• Tap heritage card → HeritageStoryActivity
• Tap style chip → GalleryFragment with filter pre-applied

SCREEN 8: GALLERY SCREEN
Screen ID: SCR-008
File: GalleryFragment.kt + fragment_gallery.xml

PURPOSE:
Full catalog of all products with search and filter capabilities.
UI LAYOUT SPECIFICATION:
text
Top Bar:
    - Title: "Gallery" (18sp, Bold)
    - Filter icon button (right)
    - Sort icon button (right)

Filter Chips Row (Horizontal ScrollView):
    Chips: "All" | "Hoysala" | "Dravidian" | "Chola" | "Black Granite" | "Sandstone" | "Available"
    Active Chip: Background #D4A843, Text #3E2723
    Inactive Chip: Outline style, #3E2723

Sort Bottom Sheet (opens on sort tap):
    Options:
    - "Latest First" (default)
    - "Price: Low to High"
    - "Price: High to Low"
    - "Most Reviewed"
    - "Top Rated"

Product Grid:
    - 2 columns, StaggeredGridLayoutManager
    - Each card:
        → High-res image (Glide with DiskCacheStrategy.ALL)
        → Shimmer while loading
        → Product title
        → Price (Gold color)
        → Material tag
        → Rating stars
        → Stone freshness indicator
        → Availability status
    
Filter Bottom Sheet (full filter panel):
    - Material: Multi-select chips
    - Carving Style: Multi-select chips
    - Price Range: RangeSlider (₹0 to ₹10,00,000)
    - Availability: Toggle (All / Available Only)
    - "Apply Filters" button
    - "Reset" button

Empty State:
    - Illustration image
    - "No sculptures found" text
    - "Clear Filters" button

Pagination:
    - Load 20 items at first
    - Load more as user scrolls near bottom
    - Loading spinner at bottom while loading more
VIEWMODEL LOGIC:
Kotlin
class GalleryViewModel : ViewModel() {

    private val productRepo = ProductRepository()
    
    val products = MutableLiveData<List<Product>>()
    val isLoading = MutableLiveData<Boolean>()
    val isLoadingMore = MutableLiveData<Boolean>()
    val hasMoreItems = MutableLiveData<Boolean>(true)

    private var currentFilters = ProductFilters()
    private var lastDocument: DocumentSnapshot? = null
    private val pageSize = 20

    fun loadProducts(filters: ProductFilters = ProductFilters()) {
        currentFilters = filters
        lastDocument = null
        
        viewModelScope.launch {
            isLoading.value = true
            val result = productRepo.getProducts(filters, pageSize, null)
            products.value = result.items
            lastDocument = result.lastDocument
            hasMoreItems.value = result.items.size == pageSize
            isLoading.value = false
        }
    }

    fun loadMoreProducts() {
        if (isLoadingMore.value == true || hasMoreItems.value == false) return
        
        viewModelScope.launch {
            isLoadingMore.value = true
            val result = productRepo.getProducts(currentFilters, pageSize, lastDocument)
            val currentList = products.value?.toMutableList() ?: mutableListOf()
            currentList.addAll(result.items)
            products.value = currentList
            lastDocument = result.lastDocument
            hasMoreItems.value = result.items.size == pageSize
            isLoadingMore.value = false
        }
    }
}
NAVIGATION FROM THIS SCREEN:
• Tap product → ProductDetailActivity
• Filter/Sort → bottom sheets (stay on same screen)

SCREEN 9: PRODUCT DETAIL SCREEN
Screen ID: SCR-009
File: ProductDetailActivity.kt + activity_product_detail.xml

PURPOSE:
The most important buyer screen. Shows full product details, image gallery, seller info, work-in-progress, and enables WhatsApp inquiry.
UI LAYOUT SPECIFICATION:
text
TOP — Image Gallery Section:
    ViewPager2 with horizontal swipe
    Image height: 300dp
    Each image: Glide loaded, HIGH-RES, fitCenter
    Tap any image → opens FullScreenImageActivity
    Dot indicators (bottom of image area)
    Image count badge: "3/7" (top-right corner)
    Back button (top-left, semi-transparent circle)
    Bookmark button (top-right, semi-transparent circle)
    Share button (top-right, semi-transparent circle)

SECTION 1 — Product Identity:
    Product Title: 20sp, Bold, #3E2723
    Product ID: "ID: SKS-2025-0042" — 12sp, monospace, #9E9E9E
    Price: "₹45,000" — 24sp, Bold, #D4A843
    Availability Chip: "Available" (green) / "Sold" (red) / "On Order" (orange)

SECTION 2 — Quick Specs Row:
    4 spec pills in a row:
    1. Material icon + "Black Granite"
    2. Style icon + "Hoysala"
    3. Weight icon + "28 kg"
    4. Dimensions icon + "2.5 × 1.2 ft"

SECTION 3 — Stone Freshness:
    Label: "Stone Freshness"
    Value: "Fresh Cut" / "Aged" / "Antique Finish"
    Color indicator dot:
        Fresh = Green
        Aged = Orange
        Antique = Brown

SECTION 4 — Description:
    Title: "About This Piece"
    Expandable text (2 lines collapsed, "Read More" link)
    Full description on expand

SECTION 5 — Shilpi (Seller) Card:
    Layout: Horizontal card
    CircleImageView: Seller photo (56dp)
    Verified badge overlay if isVerifiedArtisan = true
        (Blue checkmark badge, 16dp, bottom-right of circle)
    Name: "Ravi Kumar" (16sp, Bold)
    Village: "Shivarapatna, Karnataka" (12sp, Grey)
    Rating: Stars + "(42 reviews)"
    Experience: "28 years experience"
    Specialty: "Hoysala Style Expert"
    "View Portfolio" button → SellerPortfolioActivity

SECTION 6 — Work in Progress Preview:
    Title: "Creation Journey"
    Horizontal scroll of 3 timeline stages
    Each: small image + stage name + date
    "See Full Timeline" button → WorkInProgressActivity

SECTION 7 — Ratings & Reviews:
    Overall rating: Large number (4.8) + Stars
    Rating breakdown bars (5★ through 1★)
    Top 2 reviews displayed
    "View All Reviews" link → ReviewsActivity
    "Write a Review" button (only for verified buyers)

SECTION 8 — Heritage Tag:
    If product has a carving style:
    Card with style info teaser
    "Learn About Hoysala Style" button → HeritageStoryActivity

BOTTOM — Fixed Action Bar:
    Height: 72dp
    Background: White with top shadow
    Left: Price "₹45,000"
    Right: "Enquire on WhatsApp" button
        Background: #25D366 (WhatsApp Green)
        Icon: WhatsApp logo (white)
        Text: "Enquire Now" (white, bold)
        Corner Radius: 8dp
        Width: 60% of screen
WHATSAPP INTEGRATION LOGIC:
Kotlin
class ProductDetailActivity : AppCompatActivity() {

    private fun openWhatsApp(product: Product, seller: Seller) {
        
        val productId = product.productId
        val productName = product.title
        val price = product.price
        val material = product.material
        val sellerWhatsApp = seller.whatsappNumber

        // Pre-filled WhatsApp message with Product ID
        val message = """
            Hello! I'm interested in this sculpture from Shilpa-Kala Showcase.
            
            *Product Details:*
            📦 Product ID: $productId
            🗿 Name: $productName
            💰 Price: ₹$price
            🪨 Material: $material
            
            Could you please provide more information?
            
            Sent via Shilpa-Kala Showcase App
        """.trimIndent()

        val encodedMessage = URLEncoder.encode(message, "UTF-8")
        val whatsappUri = "https://wa.me/$sellerWhatsApp?text=$encodedMessage"

        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(whatsappUri))
            intent.setPackage("com.whatsapp")
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            // WhatsApp not installed — fallback to browser
            try {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(whatsappUri))
                startActivity(browserIntent)
            } catch (e2: Exception) {
                // Final fallback — direct call
                val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$sellerWhatsApp"))
                startActivity(callIntent)
                Toast.makeText(this, "WhatsApp not found. Calling instead.", Toast.LENGTH_SHORT).show()
            }
        }
        
        // Track inquiry in Firestore analytics
        trackInquiry(productId)
    }

    private fun trackInquiry(productId: String) {
        // Increment inquiry count in Firestore
        val db = FirebaseFirestore.getInstance()
        db.collection("products").document(productId)
            .update("inquiryCount", FieldValue.increment(1))
    }
}
NAVIGATION FROM THIS SCREEN:
• Tap image → FullScreenImageActivity
• "View Portfolio" → SellerPortfolioActivity
• "See Full Timeline" → WorkInProgressActivity
• "View All Reviews" → ReviewsActivity
• "Learn About Style" → HeritageStoryActivity
• "Enquire Now" → WhatsApp (external app)
• Back → GalleryFragment or BuyerHomeFragment

SCREEN 10: FULL-SCREEN IMAGE VIEWER
Screen ID: SCR-010
File: FullScreenImageActivity.kt + activity_fullscreen_image.xml

PURPOSE:
Immersive full-screen viewing of sculpture images with pinch-to-zoom.
UI LAYOUT SPECIFICATION:
text
Background: #000000 (Pure Black)

Main View:
    PhotoView (from chrisbanes/PhotoView library)
    Fills entire screen
    Supports:
        - Pinch to zoom (1× to 5× maximum)
        - Double tap to zoom in/out
        - Pan when zoomed
        - Fling to scroll when zoomed

Overlay (visible on tap, hides after 3 seconds):
    Top:
        - Back button (white, X icon)
        - Image counter "3 / 7" (white)
        - Download/Share button (white)
    
    Bottom:
        - Thumbnail strip (horizontal RecyclerView)
        - Active thumbnail has gold border
        - Swipe main view to navigate images

Gesture:
    - Swipe left/right: Navigate to next/previous image
    - Pinch: Zoom in/out
    - Double tap: Toggle between 1× and 2.5×
    - Swipe down (when at 1×): Close and return

Status Bar: Hidden (full screen immersive mode)
Navigation Bar: Hidden
LOGIC:
Kotlin
class FullScreenImageActivity : AppCompatActivity() {

    private lateinit var photoView: PhotoView
    private var imageUrls: List<String> = emptyList()
    private var currentPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Full immersive mode
        window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_FULLSCREEN or
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        )

        imageUrls = intent.getStringArrayListExtra("IMAGE_URLS") ?: emptyList()
        currentPosition = intent.getIntExtra("POSITION", 0)

        setupPhotoView()
        loadImage(currentPosition)
    }

    private fun setupPhotoView() {
        photoView.minimumScale = 1f
        photoView.mediumScale = 2.5f
        photoView.maximumScale = 5f
        
        photoView.setOnViewTapListener { _, _, _ ->
            toggleOverlayVisibility()
        }
    }

    private fun loadImage(position: Int) {
        Glide.with(this)
            .load(imageUrls[position])
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.shimmer_bg)
            .error(R.drawable.ic_image_error)
            .into(photoView)
        
        updateCounter(position + 1, imageUrls.size)
    }
}
NAVIGATION FROM THIS SCREEN:
• Back button / swipe down → ProductDetailActivity

SCREEN 11: WORK IN PROGRESS TIMELINE SCREEN
Screen ID: SCR-011
File: WorkInProgressActivity.kt + activity_work_in_progress.xml

PURPOSE:
Shows the complete creation journey of a sculpture from raw stone to finished piece.
UI LAYOUT SPECIFICATION:
text
Top Bar:
    - Back button
    - Title: "Creation Journey"
    - Product name subtitle

Header Card:
    - Product primary image (small, right side)
    - Product name
    - "Work in Progress" or "Completed" badge

Timeline Layout (Vertical RecyclerView):
    
    Each Timeline Entry Card:
    
    LEFT COLUMN (Timeline indicator):
        - Colored circle with stage number
        - Vertical line connecting to next entry
        - Circle Color by stage:
            Stage 1: #795548 (Raw Stone Brown)
            Stage 2: #FF8F00 (Amber — Roughing)
            Stage 3: #1565C0 (Blue — Detailing)
            Stage 4: #2E7D32 (Green — Finishing)
            Final: #D4A843 (Gold — Complete)
    
    RIGHT COLUMN (Entry content):
        - Date: "15 Jan 2025" (12sp, Grey)
        - Stage Name: "Stage 3 — Facial Detailing" (16sp, Bold)
        - Description: Multi-line description of what was done
        - Photo: 
            Full width if landscape
            Half width if portrait
            Glide loaded with centerCrop
            Tap → FullScreenImageActivity
        - Timeline ago: "3 weeks ago" (12sp, lighter grey)

Empty State:
    - "No timeline entries yet"
    - Seller has not uploaded progress photos

Stages Reference Card (at top, collapsed by default):
    Shows expected stages:
    Block Selection → Rough Shaping → Base Design → 
    Detail Carving → Face/Feature Work → Smoothing → Final Polish
NAVIGATION FROM THIS SCREEN:
• Tap timeline image → FullScreenImageActivity
• Back → ProductDetailActivity

SCREEN 12: HERITAGE STORY SCREEN
Screen ID: SCR-012
File: HeritageStoryActivity.kt + activity_heritage_story.xml

PURPOSE:
Educational content about carving styles — Hoysala, Dravidian, Chola, etc.
UI LAYOUT SPECIFICATION:
text
Full Layout: NestedScrollView (scrollable article)

Hero Section:
    - Full-width image (250dp height)
    - Gradient overlay at bottom
    - Style name text on image: "The Hoysala Style"
        Font: Serif, 28sp, White
    - Period text: "12th – 14th Century CE"

Article Body:
    - Section: "Origins"
        Heading: 18sp, Bold, #3E2723
        Body text: 15sp, regular, #424242, line spacing 1.6
    
    - Section: "Distinctive Features"
        Bullet list with stone chisel icons
        Each feature: 14sp
    
    - Section: "Where to Find It"
        Map-style card with location names
    
    - Section: "The Shilpi's Perspective"
        Styled quote card:
        Background: #FFF8E7
        Left border: 4dp, Gold
        Italic text: Shilpi's quote
        Attribution: "— Shilpi Name, Village"

    - Section: "Sculptures in This Style"
        Horizontal scroll of related products
        Tap → ProductDetailActivity

Share Button (Fixed Bottom):
    "Share This Heritage Story"
    Share icon
    Uses Android ShareSheet
NAVIGATION FROM THIS SCREEN:
• Tap related product → ProductDetailActivity
• Back → wherever it was opened from

SCREEN 13: SEARCH SCREEN
Screen ID: SCR-013
File: SearchFragment.kt + fragment_search.xml

PURPOSE:
Full text search + advanced filters for finding specific sculptures.
UI LAYOUT SPECIFICATION:
text
Top Search Bar:
    - SearchView with real-time search
    - Hint: "Search sculptures, styles, materials..."
    - Clear button (X) inside field
    - Voice search icon

Recent Searches Section (when no query):
    - "Recent Searches" heading
    - List of last 5 searches (tappable)
    - "Clear All" button

Popular Searches (when no query):
    - "Popular Right Now" heading
    - Horizontal chips: "Ganesh", "Hoysala", "Black Granite", etc.

Filter Panel (expandable or separate sheet):
    Material Filter:
        Multi-select chips:
        "Black Granite" | "Sandstone" | "Marble" | "Limestone" | "Wood"
    
    Style Filter:
        "Hoysala" | "Dravidian" | "Chola" | "Modern" | "Traditional"
    
    Price Range:
        RangeSlider
        Min: ₹0
        Max: ₹10,00,000
        Show current range values below slider
    
    Availability:
        Toggle group: "All" | "Available Only"
    
    Stone Freshness:
        "Fresh" | "Aged" | "Antique"
    
    Rating:
        "4★ and above" | "3★ and above" | "All"

Search Results:
    - Shows as grid (same as Gallery cards)
    - Count: "34 sculptures found"
    - Sort option (same as gallery)

No Results State:
    - "No results for '[query]'"
    - Suggestions: related searches
    - "Clear Search" button
LOGIC:
Kotlin
class SearchViewModel : ViewModel() {

    private val productRepo = ProductRepository()
    val searchResults = MutableLiveData<List<Product>>()
    val isSearching = MutableLiveData<Boolean>()

    private val searchJob = MutableStateFlow("")

    init {
        // Debounce search — wait 300ms after user stops typing
        viewModelScope.launch {
            searchJob
                .debounce(300)
                .filter { it.isNotBlank() }
                .distinctUntilChanged()
                .collect { query ->
                    performSearch(query)
                }
        }
    }

    fun onSearchQueryChanged(query: String) {
        searchJob.value = query
    }

    private suspend fun performSearch(query: String) {
        isSearching.value = true
        val results = productRepo.searchProducts(query)
        searchResults.value = results
        isSearching.value = false
    }
}
NAVIGATION FROM THIS SCREEN:
• Tap result → ProductDetailActivity

SCREEN 14: SAVED / BOOKMARKS SCREEN
Screen ID: SCR-014
File: SavedFragment.kt + fragment_saved.xml

PURPOSE:
Shows all products the buyer has bookmarked/saved. Stored locally via Room DB.
UI LAYOUT SPECIFICATION:
text
Top Bar:
    - Title: "Saved Sculptures"
    - Count: "(12)" next to title
    - Sort/Edit button

Saved Grid:
    - 2 column grid (same style as gallery)
    - Each card has:
        → Product image
        → Product name
        → Price
        → "Remove" icon (heart/bookmark filled)
        → Date saved: "Saved 3 days ago"

Swipe to Delete:
    - Swipe left on any card → shows red delete action
    - Confirm delete dialog

Empty State:
    - Bookmark illustration
    - "Nothing saved yet"
    - "Explore Gallery" button → GalleryFragment

Sort Options:
    - "Recently Saved" (default)
    - "Price: Low to High"
    - "Price: High to Low"
ROOM DB LOGIC:
Kotlin
// BookmarkDao.kt
@Dao
interface BookmarkDao {
    
    @Query("SELECT * FROM bookmarks ORDER BY savedAt DESC")
    fun getAllBookmarks(): LiveData<List<BookmarkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(bookmark: BookmarkEntity)

    @Delete
    suspend fun removeBookmark(bookmark: BookmarkEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM bookmarks WHERE productId = :productId)")
    suspend fun isBookmarked(productId: String): Boolean

    @Query("DELETE FROM bookmarks")
    suspend fun clearAll()
}

// SavedViewModel.kt
class SavedViewModel(application: Application) : AndroidViewModel(application) {
    
    private val db = ShilpaKalaDatabase.getInstance(application)
    val savedProducts = db.bookmarkDao().getAllBookmarks()
    
    fun removeBookmark(bookmark: BookmarkEntity) {
        viewModelScope.launch {
            db.bookmarkDao().removeBookmark(bookmark)
        }
    }
}
NAVIGATION FROM THIS SCREEN:
• Tap saved product → ProductDetailActivity
• "Explore Gallery" → GalleryFragment

SCREEN 15: BUYER PROFILE SCREEN
Screen ID: SCR-015
File: BuyerProfileFragment.kt + fragment_buyer_profile.xml

PURPOSE:
Buyer's personal profile management and app settings.
UI LAYOUT SPECIFICATION:
text
Top Profile Section:
    - Large CircleImageView (96dp) — profile photo
    - Camera icon overlay for change photo
    - Name: "Arjun Sharma" (20sp, Bold)
    - Phone: "+91-98765-XXXXX" (masked)
    - "Verified Buyer" badge (if applicable)

Stats Row:
    3 stats in a row:
    - Saved: "12 items"
    - Inquiries: "5 made"
    - Reviews: "3 written"

Settings Sections:

    SECTION: Account
    - Edit Profile → edit name, profile photo
    - Change Language → KA / EN toggle

    SECTION: Preferences
    - Notification Settings
    - Default filter preferences

    SECTION: History
    - My Reviews → list of reviews written
    - Inquiry History

    SECTION: App Info
    - About Shilpa-Kala
    - Heritage Mission
    - Terms & Privacy
    - Rate the App
    - App Version: v1.0

    SECTION: Account Actions
    - "Log Out" (outline button, red text)
    - "Delete Account" (text only, red, small)

Logout Confirmation Dialog:
    "Are you sure you want to log out?"
    Cancel | Log Out buttons
NAVIGATION FROM THIS SCREEN:
• Logout → RoleSelectionActivity (clear back stack)
• My Reviews → ReviewsHistoryActivity

SCREEN 16: SELLER MAIN ACTIVITY (HOST)
Screen ID: SCR-016
File: SellerMainActivity.kt + activity_seller_main.xml

PURPOSE:
Container for all seller screens with seller-specific bottom navigation.
UI LAYOUT SPECIFICATION:
text
Bottom Navigation — 4 tabs:
    1. Dashboard (grid/chart icon) — "Dashboard"
    2. Portfolio (gallery icon) — "Portfolio"
    3. Add Product (+ icon, highlighted) — "Add New"
    4. Profile (person icon) — "My Profile"

Tab Colors:
    Active: #D4A843 (Gold)
    Inactive: #9E9E9E

Top Bar:
    - "Shilpa-Kala" branding
    - "Seller" badge (subtle)
    - Notification bell

SCREEN 17: SELLER DASHBOARD
Screen ID: SCR-017
File: SellerDashboardFragment.kt + fragment_seller_dashboard.xml

PURPOSE:
Overview screen for seller to see their performance stats and recent activity.
UI LAYOUT SPECIFICATION:
text
Header:
    "Namaste, [Seller Name] 🙏"
    Today's date
    Verified Artisan badge if verified

Stats Cards Row (horizontal scroll):
    Card 1: "Total Products" → count
    Card 2: "Total Views" → count
    Card 3: "Inquiries Received" → count
    Card 4: "Avg Rating" → X.X ★
    Each card: 100dp wide, Gold header, white body

Chart Section:
    "Inquiry Trends (Last 30 Days)"
    LineChart (MPAndroidChart)
    X-axis: Days
    Y-axis: Inquiry count
    Gold line color

Recent Activity Section:
    Title: "Recent Inquiries"
    Each item:
        - WhatsApp icon (green)
        - "Buyer inquired about [Product Name]"
        - Product ID
        - Time ago

Quick Actions:
    2 buttons side by side:
    [Add New Product] [View Portfolio]

Pending Actions:
    If no products: "Add your first sculpture!"
    If not verified: "Complete verification to get verified badge"
    
Verification Status Card (if not verified):
    Orange warning card
    "Your account is under review"
    "Expected: 2-3 business days"
NAVIGATION FROM THIS SCREEN:
• "Add New Product" → AddProductActivity
• "View Portfolio" → PortfolioFragment
• Tab navigation

SCREEN 18: SELLER PORTFOLIO SCREEN
Screen ID: SCR-018
File: PortfolioFragment.kt + fragment_portfolio.xml

PURPOSE:
Seller's public-facing and private product management screen.
UI LAYOUT SPECIFICATION:
text
Top Bar:
    - "My Portfolio"
    - Total count: "(8 sculptures)"
    - Grid/List toggle

Filter Tabs (horizontal):
    "All" | "Available" | "Sold" | "Inactive"

Product Grid (2 columns):
    Each card (same as gallery + management options):
        - Product image
        - Product name
        - Price
        - Availability status chip
        - View count: "👁 234 views"
        - Inquiry count: "💬 12 inquiries"
        - Rating: ★ X.X
        - Action menu (3-dot): Edit | Deactivate | Delete | Add Timeline

Empty State:
    "Your portfolio is empty"
    "Showcase your first sculpture!"
    [Add Product] button

FAB (Floating Action Button):
    + icon, Gold color
    Tap → AddProductActivity
NAVIGATION FROM THIS SCREEN:
• Tap product → ProductDetailActivity (seller's own product view with edit option)
• FAB or "Add Product" → AddProductActivity
• 3-dot → Edit → AddProductActivity with existing data
• "Add Timeline" → AddTimelineActivity

SCREEN 19: ADD PRODUCT SCREEN
Screen ID: SCR-019
File: AddProductActivity.kt + activity_add_product.xml

PURPOSE:
Seller fills in all details about a new sculpture and uploads images.
UI LAYOUT SPECIFICATION:
text
Top Bar:
    - Back arrow
    - Title: "Add New Sculpture" or "Edit Sculpture"
    - Save button (top-right, text)

Form Layout (NestedScrollView):

    STEP 1 — Images (Required):
        Title: "Product Photos *"
        Subtitle: "Add up to 10 high-quality photos"
        
        Image Grid:
            First slot: Large "+" button (Add Photo)
            Added photos: thumbnail with ✕ remove button
            Drag to reorder hint
        
        Photo Guidelines card:
            "✓ Use good natural lighting"
            "✓ Multiple angles required"
            "✓ Include size reference"
            "✗ No blurry images"

    STEP 2 — Basic Info:
        Product Title *:
            Hint: "E.g., Hoysala Ganesh Idol"
            Max: 100 characters
            Counter below
        
        Price (INR) *:
            Number input
            Hint: "Enter price in ₹"
            Prefix: "₹" symbol
        
        Availability *:
            Spinner/Dropdown: Available | Sold | On Order
        
        Dimensions:
            Hint: "Height × Width × Depth (in feet/cm)"
        
        Weight:
            Hint: "Weight in kg"

    STEP 3 — Classification:
        Material *:
            Multi-select chips or dropdown:
            Black Granite | Sandstone | Marble | Limestone | Wood | Other
        
        Carving Style *:
            Chips: Hoysala | Dravidian | Chola | Traditional | Modern | Other
        
        Stone Freshness:
            Radio: Fresh Cut | Aged | Antique Finish

    STEP 4 — Description:
        About This Piece *:
            Multi-line text area
            Min: 100 characters
            Max: 1000 characters
            Hint: "Describe the sculpture, its significance, carving details..."
        
        Carving Time:
            Hint: "How many days/months to create?"

    STEP 5 — Contact:
        WhatsApp Number for Inquiries *:
            Pre-filled with seller's registered number
            Editable if different

SUBMIT SECTION:
    Preview Section:
        Shows mini card of how the product will look
    
    "Publish Sculpture" button:
        Gold background, full width
        Shows upload progress
    
    Progress Dialog:
        "Uploading images... (3/7)"
        Progress bar
        "Publishing product..."
LOGIC:
Kotlin
class AddProductViewModel : ViewModel() {

    private val productRepo = ProductRepository()
    private val storage = FirebaseStorage.getInstance()
    
    val uploadProgress = MutableLiveData<Int>()
    val publishState = MutableLiveData<PublishState>()

    fun publishProduct(
        images: List<Uri>,
        productData: Product,
        sellerId: String
    ) {
        viewModelScope.launch {
            publishState.value = PublishState.UPLOADING_IMAGES
            
            // Upload all images to Firebase Storage
            val imageUrls = mutableListOf<String>()
            images.forEachIndexed { index, uri ->
                val url = uploadImageToStorage(uri, sellerId)
                imageUrls.add(url)
                uploadProgress.value = ((index + 1) * 100) / images.size
            }
            
            publishState.value = PublishState.SAVING_PRODUCT

            // Generate unique Product ID
            val productId = generateProductId()
            
            // Save product to Firestore
            val finalProduct = productData.copy(
                productId = productId,
                sellerId = sellerId,
                primaryImageUrl = imageUrls.first(),
                allImageUrls = imageUrls,
                createdAt = Timestamp.now(),
                isActive = true
            )
            
            productRepo.saveProduct(finalProduct)
                .onSuccess { publishState.value = PublishState.SUCCESS }
                .onFailure { publishState.value = PublishState.ERROR }
        }
    }

    private fun generateProductId(): String {
        // Format: SKS-2025-XXXXX
        val year = Calendar.getInstance().get(Calendar.YEAR)
        val random = (10000..99999).random()
        return "SKS-$year-$random"
    }

    private suspend fun uploadImageToStorage(uri: Uri, sellerId: String): String {
        val fileName = "products/$sellerId/${UUID.randomUUID()}.jpg"
        val storageRef = storage.reference.child(fileName)
        storageRef.putFile(uri).await()
        return storageRef.downloadUrl.await().toString()
    }
}
NAVIGATION FROM THIS SCREEN:
• Back → PortfolioFragment (with confirmation dialog if unsaved)
• On Success → ProductDetailActivity (of newly created product)

SCREEN 20: ADD TIMELINE ENTRY SCREEN
Screen ID: SCR-020
File: AddTimelineActivity.kt + activity_add_timeline.xml

PURPOSE:
Seller documents a new stage in the sculpture's creation process.
UI LAYOUT SPECIFICATION:
text
Top Bar:
    - Back arrow
    - Title: "Add Progress Update"
    - Product name shown as subtitle

Product Reference Card (top):
    - Thumbnail of product
    - Product name
    - Product ID
    - Current stage count: "3 stages documented"

Form:

    Stage Name *:
        Dropdown/Spinner with predefined stages:
        "1. Raw Stone Selection"
        "2. Initial Block Shaping"
        "3. Base Proportioning"
        "4. Rough Carving"
        "5. Facial Features"
        "6. Detail Work"
        "7. Smoothing & Polishing"
        "8. Final Inspection"
        "Custom Stage..." (allows typing)

    Stage Photo *:
        Large camera/gallery button
        Preview after selection
        Only 1 photo per timeline entry

    Description *:
        "What was done in this stage?"
        Multi-line input
        Min: 50 characters

    Date:
        DatePicker (defaults to today)
        Can be backdated

    Time Spent:
        "Hours spent on this stage"
        Number input

SAVE BUTTON:
    "Save Progress Update"
    Gold, full width
    
Timeline Preview (at bottom):
    Shows all existing timeline entries in compact form
    New entry will be added at top
NAVIGATION FROM THIS SCREEN:
• Back → PortfolioFragment
• On Success → WorkInProgressActivity (showing the updated timeline)

SCREEN 21: SELLER PROFILE SCREEN
Screen ID: SCR-021
File: SellerProfileFragment.kt + fragment_seller_profile.xml

PURPOSE:
Seller manages their public artisan profile visible to all buyers.
UI LAYOUT SPECIFICATION:
text
Top Profile Card:
    Background: Heritage gradient (#3E2723 → #5D4037)
    CircleImageView (96dp) — profile photo
    Camera button overlay
    Name: Bold, White, 22sp
    "Verified Artisan" badge: Gold, with checkmark icon
    Village: White, 14sp
    Specialty: White italic, 13sp
    Rating: Stars, White

Profile Completion Progress:
    If not complete:
    "Complete your profile to get more inquiries!"
    Progress bar: 60% complete
    Checklist of missing items

Editable Fields:

    Basic Info:
    - Full Name (Kannada + English)
    - Village / Town
    - District, State

    Professional Info:
    - Specialty Description
    - Carving Styles (multi-select chips)
    - Years of Experience (number)
    - Materials Worked With (chips)

    Bio / Story:
    - "Tell buyers about your craft journey"
    - Multi-line text area
    - 500 character max

    Contact:
    - WhatsApp Number for buyers
    - Phone Number (optional public)

    Verification Status:
    - "Verified Artisan" = shows badge
    - "Pending Review" = shows orange status
    - "Not Submitted" = shows "Apply for Verification" button

Verification Process Info Card:
    "How to get Verified Artisan badge:"
    1. Submit ID proof
    2. Send 3 sample product photos
    3. Admin review (2-3 days)
    4. Badge added to profile

Save Button:
    "Update Profile"
    Gold, full width

Logout:
    "Log Out" — outline, red text

Preview as Buyer:
    "How buyers see my profile"
    Opens SellerPublicProfileActivity

SCREEN 22: SELLER PUBLIC PORTFOLIO (Buyer View)
Screen ID: SCR-022
File: SellerPortfolioActivity.kt + activity_seller_portfolio.xml

PURPOSE:
This is what buyers see when they tap a seller's name or "View Portfolio". It shows the seller's full profile as a public showcase.
UI LAYOUT SPECIFICATION:
text
Hero Section:
    Large header image (could be seller's best work)
    Height: 220dp
    Gradient overlay

Seller Identity Card (overlapping hero):
    White card, elevation 8dp
    CircleImageView: Seller photo (72dp)
    Verified Artisan badge (if verified)
    Name: 20sp, Bold
    Village: 13sp, Grey
    Specialty: 14sp, #D4A843 (Gold)
    Experience: "28 years of mastery"
    Rating: Stars + review count
    Bio: 3 lines, expandable

Stats Row:
    "42 Sculptures" | "4.8★ Rating" | "156 Buyers"

Contact Section:
    "Enquire About Custom Work" button:
        WhatsApp Green (#25D366)
        Opens WhatsApp with pre-filled message (no product ID, general inquiry)
    "Call" button (if seller chose to show phone)

Portfolio Grid:
    All seller's active products
    Same card style as main gallery
    Tap → ProductDetailActivity

Heritage Specialty Card:
    "Specializes in Hoysala Style"
    Brief description
    "Learn More" → HeritageStoryActivity
NAVIGATION FROM THIS SCREEN:
• Tap product → ProductDetailActivity
• "Enquire" → WhatsApp
• "Learn More" → HeritageStoryActivity
• Back → wherever it was opened from

SCREEN 23: REVIEWS SCREEN
Screen ID: SCR-023
File: ReviewsActivity.kt + activity_reviews.xml

PURPOSE:
Full reviews screen for a product with option to write a review.
UI LAYOUT SPECIFICATION:
text
Top Bar:
    Back arrow
    "Reviews — [Product Name]"

Summary Card:
    Large rating number: "4.8"
    5 stars displayed
    "Based on 42 reviews"
    
    Rating Breakdown Bars:
    5★ ██████████ 68%
    4★ ████░░░░░░ 22%
    3★ ██░░░░░░░░  7%
    2★ █░░░░░░░░░  2%
    1★ ░░░░░░░░░░  1%

Write Review Button (for buyers only):
    "Write a Review"
    Only visible if user is a buyer
    Opens write review bottom sheet

Reviews List:
    Sort options: "Most Recent" | "Highest Rated" | "Lowest Rated"
    
    Each Review Card:
        - CircleImageView: Buyer photo (36dp)
        - Buyer name (anonymized: "Arjun S.")
        - Star rating
        - Date: "15 Jan 2025"
        - Review text
        - "Verified Buyer" tag (if applicable)
        - Helpful? 👍 count

Write Review Bottom Sheet:
    "Rate Your Experience"
    5 interactive stars
    "Write your review" text field (min 20 chars)
    "Anonymous review" checkbox
    "Submit Review" button

PART 5: SCREEN CONNECTION MAP

5.1 Complete Navigation Flow Diagram
text
APP LAUNCH
    │
    ▼
SplashActivity (SCR-001)
    │
    ├──► OnboardingActivity (SCR-002) [First time]
    │        │
    │        ▼
    │    RoleSelectionActivity (SCR-003)
    │
    └──► RoleSelectionActivity (SCR-003) [Returning, not logged in]
    │
    └──► BuyerMainActivity (SCR-006) [Logged in buyer]
    │
    └──► SellerMainActivity (SCR-016) [Logged in seller]

═══════════════════════════════════════════════════
ROLE SELECTION FLOW
═══════════════════════════════════════════════════

RoleSelectionActivity (SCR-003)
    │
    ▼
PhoneAuthActivity (SCR-004)
    │
    ▼
OtpVerificationActivity (SCR-005)
    │
    ├──► BuyerMainActivity (SCR-006) [if role=buyer]
    │
    └──► SellerMainActivity (SCR-016) [if role=seller]

═══════════════════════════════════════════════════
BUYER NAVIGATION TREE
═══════════════════════════════════════════════════

BuyerMainActivity (SCR-006)
    │
    ├── [TAB 1] BuyerHomeFragment (SCR-007)
    │       │
    │       ├──► ProductDetailActivity (SCR-009) [tap product]
    │       ├──► SellerPortfolioActivity (SCR-022) [tap seller]
    │       ├──► HeritageStoryActivity (SCR-012) [tap heritage card]
    │       └──► GalleryFragment (SCR-008) [See All]
    │
    ├── [TAB 2] GalleryFragment (SCR-008)
    │       │
    │       └──► ProductDetailActivity (SCR-009) [tap product]
    │
    ├── [TAB 3] SearchFragment (SCR-013)
    │       │
    │       └──► ProductDetailActivity (SCR-009) [tap result]
    │
    ├── [TAB 4] SavedFragment (SCR-014)
    │       │
    │       └──► ProductDetailActivity (SCR-009) [tap saved item]
    │
    └── [TAB 5] BuyerProfileFragment (SCR-015)
            │
            └──► RoleSelectionActivity (SCR-003) [logout]

ProductDetailActivity (SCR-009)
    │
    ├──► FullScreenImageActivity (SCR-010) [tap image]
    ├──► WorkInProgressActivity (SCR-011) [See Timeline]
    ├──► HeritageStoryActivity (SCR-012) [Learn Style]
    ├──► SellerPortfolioActivity (SCR-022) [View Portfolio]
    ├──► ReviewsActivity (SCR-023) [View Reviews]
    └──► WhatsApp (External) [Enquire Now]

═══════════════════════════════════════════════════
SELLER NAVIGATION TREE
═══════════════════════════════════════════════════

SellerMainActivity (SCR-016)
    │
    ├── [TAB 1] SellerDashboardFragment (SCR-017)
    │       │
    │       ├──► AddProductActivity (SCR-019)
    │       └──► PortfolioFragment (SCR-018)
    │
    ├── [TAB 2] PortfolioFragment (SCR-018)
    │       │
    │       ├──► ProductDetailActivity (SCR-009) [view product]
    │       ├──► AddProductActivity (SCR-019) [add/edit]
    │       └──► AddTimelineActivity (SCR-020) [add timeline]
    │
    ├── [TAB 3] AddProductActivity (SCR-019) [direct from nav]
    │       │
    │       └──► ProductDetailActivity (SCR-009) [on success]
    │
    └── [TAB 4] SellerProfileFragment (SCR-021)
            │
            ├──► SellerPortfolioActivity (SCR-022) [preview]
            └──► RoleSelectionActivity (SCR-003) [logout]
5.2 Screen to Screen Connection Table
From ScreenActionTo ScreenData PassedSplash (001)AutoOnboarding (002)NothingSplash (001)Auto (returning)RoleSelection (003)NothingSplash (001)Auto (logged in buyer)BuyerMain (006)NothingSplash (001)Auto (logged in seller)SellerMain (016)NothingOnboarding (002)Complete/SkipRoleSelection (003)NothingRoleSelection (003)Select role → ContinuePhoneAuth (004)USER_ROLE stringPhoneAuth (004)Send OTP successOtpVerify (005)VERIFICATION_ID, PHONE_NUMBER, USER_ROLEOtpVerify (005)Verify success (buyer)BuyerMain (006)Clears stackOtpVerify (005)Verify success (seller)SellerMain (016)Clears stackBuyerHome (007)Tap productProductDetail (009)PRODUCT_IDBuyerHome (007)Tap sellerSellerPortfolio (022)SELLER_IDBuyerHome (007)Tap heritageHeritageStory (012)STORY_IDGallery (008)Tap productProductDetail (009)PRODUCT_IDProductDetail (009)Tap imageFullScreen (010)IMAGE_URLS list, POSITIONProductDetail (009)See TimelineWorkInProgress (011)PRODUCT_IDProductDetail (009)Learn StyleHeritageStory (012)STYLE_NAMEProductDetail (009)View PortfolioSellerPortfolio (022)SELLER_IDProductDetail (009)View ReviewsReviews (023)PRODUCT_IDProductDetail (009)Enquire NowWhatsApp (external)Pre-filled messageSearch (013)Tap resultProductDetail (009)PRODUCT_IDSaved (014)Tap itemProductDetail (009)PRODUCT_IDSellerDash (017)Add ProductAddProduct (019)Nothing (new)Portfolio (018)Tap productProductDetail (009)PRODUCT_IDPortfolio (018)Edit productAddProduct (019)PRODUCT_ID (edit mode)Portfolio (018)Add TimelineAddTimeline (020)PRODUCT_IDAddProduct (019)SuccessProductDetail (009)PRODUCT_ID (new)AddTimeline (020)SuccessWorkInProgress (011)PRODUCT_IDSellerProfile (021)PreviewSellerPortfolio (022)SELLER_ID (own)Any screenLogoutRoleSelection (003)Clears stack
PART 6: FIREBASE CONFIGURATION

6.1 Firebase Setup Steps
text
1. Go to https://console.firebase.google.com
2. Create new project: "shilpa-kala-showcase"
3. Add Android app:
   Package: com.shilpakala.showcase
4. Download google-services.json
5. Place in: app/google-services.json
6. Enable in Firebase Console:
   ✓ Authentication → Phone
   ✓ Firestore Database → Create in Production mode
   ✓ Storage → Create bucket
   ✓ Analytics → Enable
6.2 Firestore Security Rules
JavaScript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    
    // Users can read/write their own data
    match /users/{userId} {
      allow read, write: if request.auth != null && request.auth.uid == userId;
    }
    
    // Sellers profile — public read, own write
    match /sellers/{sellerId} {
      allow read: if true;
      allow write: if request.auth != null && request.auth.uid == sellerId;
    }
    
    // Products — public read, only seller can write
    match /products/{productId} {
      allow read: if true;
      allow create: if request.auth != null;
      allow update, delete: if request.auth != null && 
        request.auth.uid == resource.data.sellerId;
    }
    
    // Timeline — public read, only product's seller can write
    match /timeline/{productId}/entries/{entryId} {
      allow read: if true;
      allow write: if request.auth != null;
    }
    
    // Reviews — public read, authenticated write
    match /reviews/{productId}/entries/{reviewId} {
      allow read: if true;
      allow create: if request.auth != null;
      allow update, delete: if request.auth != null && 
        request.auth.uid == resource.data.buyerId;
    }
    
    // Heritage stories — public read, no write from app
    match /heritage_stories/{storyId} {
      allow read: if true;
      allow write: if false; // Admin only via console
    }
  }
}
6.3 Firebase Storage Rules
JavaScript
rules_version = '2';
service firebase.storage {
  match /b/{bucket}/o {
    
    match /products/{sellerId}/{imageFile} {
      allow read: if true;
      allow write: if request.auth != null && request.auth.uid == sellerId
                   && request.resource.size < 10 * 1024 * 1024 // Max 10MB
                   && request.resource.contentType.matches('image/.*');
    }
    
    match /profiles/{userId}/{imageFile} {
      allow read: if true;
      allow write: if request.auth != null && request.auth.uid == userId;
    }
  }
}

PART 7: KEY UTILITY IMPLEMENTATIONS

7.1 WhatsApp Helper (Complete Implementation)
Kotlin
// utils/WhatsAppHelper.kt
object WhatsAppHelper {

    fun openProductInquiry(
        context: Context,
        product: Product,
        sellerWhatsApp: String
    ) {
        val message = buildProductMessage(product)
        openWhatsApp(context, sellerWhatsApp, message)
    }

    fun openGeneralInquiry(
        context: Context,
        seller: Seller
    ) {
        val message = """
            Hello ${seller.name}! I found your profile on Shilpa-Kala Showcase app.
            I'm interested in your work. Could we discuss?
            
            Sent via Shilpa-Kala Showcase App
        """.trimIndent()
        openWhatsApp(context, seller.whatsappNumber, message)
    }

    private fun buildProductMessage(product: Product): String {
        return """
            Namaste! 🙏
            
            I'm interested in this sculpture from *Shilpa-Kala Showcase*.
            
            📦 *Product ID:* ${product.productId}
            🗿 *Name:* ${product.title}
            💰 *Listed Price:* ₹${formatPrice(product.price)}
            🪨 *Material:* ${product.material}
            🎨 *Style:* ${product.carvingStyle}
            📐 *Dimensions:* ${product.dimensions}
            
            Could you please share more details about:
            • Availability
            • Shipping options
            • Any customization possible
            
            Thank you!
            *Sent via Shilpa-Kala Showcase App*
        """.trimIndent()
    }

    private fun openWhatsApp(
        context: Context,
        phoneNumber: String,
        message: String
    ) {
        // Clean phone number — remove spaces, dashes, parentheses
        val cleanNumber = phoneNumber.replace(Regex("[^0-9+]"), "")
        
        // Ensure country code
        val numberWithCode = if (cleanNumber.startsWith("+")) {
            cleanNumber
        } else if (cleanNumber.startsWith("91")) {
            "+$cleanNumber"
        } else {
            "+91$cleanNumber"
        }

        val encodedMessage = URLEncoder.encode(message, "UTF-8")
        val whatsappUrl = "https://wa.me/${numberWithCode.removePrefix("+")}?text=$encodedMessage"

        try {
            // Try opening WhatsApp directly
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(whatsappUrl))
            intent.setPackage("com.whatsapp")
            context.startActivity(intent)
        } catch (e1: ActivityNotFoundException) {
            try {
                // Try WhatsApp Business
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(whatsappUrl))
                intent.setPackage("com.whatsapp.w4b")
                context.startActivity(intent)
            } catch (e2: ActivityNotFoundException) {
                try {
                    // Open in browser
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(whatsappUrl))
                    context.startActivity(intent)
                } catch (e3: Exception) {
                    // Final fallback — dial
                    val callIntent = Intent(
                        Intent.ACTION_DIAL,
                        Uri.parse("tel:$numberWithCode")
                    )
                    context.startActivity(callIntent)
                    Toast.makeText(
                        context,
                        "WhatsApp not available. Opening dialer.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun formatPrice(price: Long): String {
        return String.format("%,d", price)
    }
}
7.2 Glide Configuration
Kotlin
// GlideHelper.kt
@GlideModule
class ShilpaKalaGlideModule : AppGlideModule() {

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setMemoryCache(LruResourceCache(30L * 1024 * 1024)) // 30MB memory cache
        builder.setDiskCache(
            InternalCacheDiskCacheFactory(context, 500L * 1024 * 1024) // 500MB disk cache
        )
    }
}

// Extension functions for easy Glide usage
fun ImageView.loadProductImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(ShimmerDrawable().apply { /* shimmer config */ })
        .error(R.drawable.ic_image_placeholder)
        .centerCrop()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

fun ImageView.loadCircleImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.ic_person_placeholder)
        .error(R.drawable.ic_person_placeholder)
        .circleCrop()
        .into(this)
}

fun ImageView.loadFullImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.shimmer_bg)
        .error(R.drawable.ic_image_error)
        .fitCenter()
        .into(this)
}
7.3 OTP Timer Helper
Kotlin
// OtpHelper.kt
class OtpCountdownTimer(
    private val totalTimeSeconds: Long = 60,
    private val onTick: (secondsRemaining: Long) -> Unit,
    private val onFinish: () -> Unit
) {
    private var countDownTimer: CountDownTimer? = null

    fun start() {
        countDownTimer?.cancel()
        countDownTimer = object : CountDownTimer(
            totalTimeSeconds * 1000,
            1000
        ) {
            override fun onTick(millisUntilFinished: Long) {
                onTick(millisUntilFinished / 1000)
            }

            override fun onFinish() {
                onFinish()
            }
        }.start()
    }

    fun cancel() {
        countDownTimer?.cancel()
    }
}

PART 8: UI DESIGN SPECIFICATIONS

8.1 Color Palette (colors.xml)
XML
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <!-- Primary Heritage Colors -->
    <color name="heritage_brown_dark">#3E2723</color>
    <color name="heritage_brown_medium">#5D4037</color>
    <color name="heritage_brown_light">#8D6E63</color>
    <color name="heritage_gold_primary">#D4A843</color>
    <color name="heritage_gold_light">#F4D03F</color>
    <color name="heritage_cream">#FDF8F0</color>
    <color name="heritage_warm_white">#FAFAF7</color>

    <!-- Status Colors -->
    <color name="status_available">#2E7D32</color>
    <color name="status_sold">#C62828</color>
    <color name="status_on_order">#E65100</color>
    <color name="status_pending">#F57F17</color>

    <!-- WhatsApp -->
    <color name="whatsapp_green">#25D366</color>
    <color name="whatsapp_dark">#075E54</color>

    <!-- Stone Freshness -->
    <color name="freshness_fresh">#1B5E20</color>
    <color name="freshness_aged">#E65100</color>
    <color name="freshness_antique">#4E342E</color>

    <!-- General UI -->
    <color name="text_primary">#1A1A1A</color>
    <color name="text_secondary">#757575</color>
    <color name="text_hint">#BDBDBD</color>
    <color name="divider_color">#E0E0E0</color>
    <color name="background_white">#FFFFFF</color>
    <color name="background_grey">#F5F5F5</color>
    <color name="error_red">#B71C1C</color>
    <color name="verified_blue">#1565C0</color>
</resources>
8.2 Typography (styles.xml)
XML
<?xml version="1.0" encoding="utf-8"?>
<resources>

    <!-- Screen Titles -->
    <style name="TextStyle.ScreenTitle">
        <item name="android:fontFamily">@font/serif_bold</item>
        <item name="android:textSize">22sp</item>
        <item name="android:textColor">@color/heritage_brown_dark</item>
        <item name="android:textStyle">bold</item>
    </style>

    <!-- Section Headings -->
    <style name="TextStyle.SectionHeading">
        <item name="android:fontFamily">@font/sans_semi_bold</item>
        <item name="android:textSize">18sp</item>
        <item name="android:textColor">@color/heritage_brown_dark</item>
        <item name="android:textStyle">bold</item>
    </style>

    <!-- Product Title in Cards -->
    <style name="TextStyle.ProductTitle">
        <item name="android:fontFamily">@font/sans_medium</item>
        <item name="android:textSize">14sp</item>
        <item name="android:textColor">@color/text_primary</item>
        <item name="android:maxLines">2</item>
        <item name="android:ellipsize">end</item>
    </style>

    <!-- Price Text -->
    <style name="TextStyle.Price">
        <item name="android:fontFamily">@font/sans_bold</item>
        <item name="android:textSize">16sp</item>
        <item name="android:textColor">@color/heritage_gold_primary</item>
        <item name="android:textStyle">bold</item>
    </style>

    <!-- Heritage Story Body -->
    <style name="TextStyle.ArticleBody">
        <item name="android:fontFamily">@font/serif_regular</item>
        <item name="android:textSize">15sp</item>
        <item name="android:textColor">@color/text_primary</item>
        <item name="android:lineSpacingMultiplier">1.6</item>
    </style>

    <!-- Buttons -->
    <style name="Button.Primary">
        <item name="android:backgroundTint">@color/heritage_brown_dark</item>
        <item name="android:textColor">@color/background_white</item>
        <item name="android:textStyle">bold</item>
        <item name="android:textSize">16sp</item>
        <item name="cornerRadius">8dp</item>
        <item name="android:padding">14dp</item>
    </style>

    <style name="Button.WhatsApp">
        <item name="android:backgroundTint">@color/whatsapp_green</item>
        <item name="android:textColor">@color/background_white</item>
        <item name="android:textStyle">bold</item>
        <item name="android:textSize">16sp</item>
        <item name="cornerRadius">8dp</item>
    </style>

    <style name="Button.Gold">
        <item name="android:backgroundTint">@color/heritage_gold_primary</item>
        <item name="android:textColor">@color/heritage_brown_dark</item>
        <item name="android:textStyle">bold</item>
        <item name="android:textSize">16sp</item>
        <item name="cornerRadius">8dp</item>
    </style>
</resources>
8.3 Manifest Configuration
XML
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.shilpakala.showcase">

    <!-- Permissions -->
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"
        android:maxSdkVersion="32" />
    <uses-permission android:name="android.permission.READ_MEDIA_IMAGES" />
    <uses-permission android:name="android.permission.CAMERA" />
    <uses-permission android:name="android.permission.CALL_PHONE" />
    <uses-permission android:name="android.permission.VIBRATE" />

    <application
        android:name=".ShilpaKalaApplication"
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.ShilpaKala"
        android:hardwareAccelerated="true"
        android:largeHeap="true">

        <!-- Splash -->
        <activity android:name=".ui.splash.SplashActivity"
            android:exported="true"
            android:theme="@style/Theme.ShilpaKala.Splash">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <!-- Auth Activities -->
        <activity android:name=".ui.onboarding.OnboardingActivity" />
        <activity android:name=".ui.auth.RoleSelectionActivity" />
        <activity android:name=".ui.auth.PhoneAuthActivity" />
        <activity android:name=".ui.auth.OtpVerificationActivity" />

        <!-- Buyer Activities -->
        <activity android:name=".ui.buyer.BuyerMainActivity" />
        <activity android:name=".ui.buyer.detail.ProductDetailActivity" />
        <activity android:name=".ui.buyer.fullscreen.FullScreenImageActivity"
            android:theme="@style/Theme.ShilpaKala.FullScreen" />
        <activity android:name=".ui.buyer.timeline.WorkInProgressActivity" />
        <activity android:name=".ui.buyer.heritage.HeritageStoryActivity" />
        <activity android:name=".ui.shared.ReviewsActivity" />
        <activity android:name=".ui.shared.SellerPortfolioActivity" />

        <!-- Seller Activities -->
        <activity android:name=".ui.seller.SellerMainActivity" />
        <activity android:name=".ui.seller.addproduct.AddProductActivity" />
        <activity android:name=".ui.seller.addtimeline.AddTimelineActivity" />

        <!-- File Provider for Camera -->
        <provider
            android:name="androidx.core.content.FileProvider"
            android:authorities="${applicationId}.provider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/file_paths" />
        </provider>

    </application>
</manifest>

PART 9: CONSTANTS & CONFIGURATION

9.1 Constants.kt
Kotlin
object Constants {

    // Firebase Collections
    const val COLLECTION_USERS = "users"
    const val COLLECTION_SELLERS = "sellers"
    const val COLLECTION_PRODUCTS = "products"
    const val COLLECTION_TIMELINE = "timeline"
    const val COLLECTION_REVIEWS = "reviews"
    const val COLLECTION_HERITAGE = "heritage_stories"

    // Intent Extras
    const val EXTRA_PRODUCT_ID = "PRODUCT_ID"
    const val EXTRA_SELLER_ID = "SELLER_ID"
    const val EXTRA_STORY_ID = "STORY_ID"
    const val EXTRA_USER_ROLE = "USER_ROLE"
    const val EXTRA_VERIFICATION_ID = "VERIFICATION_ID"
    const val EXTRA_PHONE_NUMBER = "PHONE_NUMBER"
    const val EXTRA_IMAGE_URLS = "IMAGE_URLS"
    const val EXTRA_IMAGE_POSITION = "POSITION"

    // User Roles
    const val ROLE_BUYER = "buyer"
    const val ROLE_SELLER = "seller"

    // Product Status
    const val STATUS_AVAILABLE = "Available"
    const val STATUS_SOLD = "Sold"
    const val STATUS_ON_ORDER = "On Order"

    // Stone Freshness
    const val FRESHNESS_FRESH = "Fresh"
    const val FRESHNESS_AGED = "Aged"
    const val FRESHNESS_ANTIQUE = "Antique Finish"

    // Carving Styles
    val CARVING_STYLES = listOf("Hoysala", "Dravidian", "Chola", "Traditional", "Modern")

    // Materials
    val MATERIALS = listOf("Black Granite", "Sandstone", "Marble", "Limestone", "Wood")

    // Pagination
    const val PAGE_SIZE = 20

    // Image Upload
    const val MAX_IMAGE_COUNT = 10
    const val MAX_IMAGE_SIZE_MB = 10

    // Product ID Format
    const val PRODUCT_ID_PREFIX = "SKS"

    // Glide Cache
    const val DISK_CACHE_SIZE_MB = 500L
    const val MEMORY_CACHE_SIZE_MB = 30L

    // OTP
    const val OTP_TIMEOUT_SECONDS = 60L
    const val OTP_LENGTH = 6

    // Prefs Keys
    const val PREF_USER_ID = "user_id"
    const val PREF_USER_ROLE = "user_role"
    const val PREF_ONBOARDING_DONE = "onboarding_done"
    const val PREF_LANGUAGE = "language"

    // Language Options
    const val LANG_ENGLISH = "en"
    const val LANG_KANNADA = "kn"

    // WhatsApp Package
    const val WHATSAPP_PACKAGE = "com.whatsapp"
    const val WHATSAPP_BUSINESS_PACKAGE = "com.whatsapp.w4b"
}

PART 10: NON-FUNCTIONAL REQUIREMENTS & PERFORMANCE

10.1 Performance Requirements
MetricTargetImplementationCold start time< 3 secondsLazy initialization, minimal work in Application classImage load time< 1.5 sec on 4GGlide with disk cache + thumbnail firstScroll FPS≥ 55 FPSRecyclerView optimization, ViewHolder patternMemory usage< 150MBGlide memory limits, avoid large allocationsCrash rate< 0.5%Global exception handler, Firebase CrashlyticsOffline browsingCached images availableDiskCacheStrategy.ALL10.2 RecyclerView Optimization Checklist
Kotlin
// Apply these to all RecyclerViews
recyclerView.apply {
    setHasFixedSize(true)           // If size doesn't change
    itemAnimator = null             // Disable animations for performance
    recycledViewPool.setMaxRecycledViews(0, 20)
    
    // For GridLayoutManager
    layoutManager = GridLayoutManager(context, 2).apply {
        recycleChildrenOnDetach = true
    }
    
    // Prefetch items
    (layoutManager as? LinearLayoutManager)?.initialPrefetchItemCount = 4
}
10.3 Error Handling Strategy
Kotlin
// Global error handling in every ViewModel
sealed class UiState<out T> {
    object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String, val code: Int = -1) : UiState<Nothing>()
    object Empty : UiState<Nothing>()
}

// Network error handling
fun handleNetworkError(throwable: Throwable): String {
    return when (throwable) {
        is UnknownHostException -> "No internet connection"
        is SocketTimeoutException -> "Request timed out. Please try again."
        is HttpException -> when (throwable.code()) {
            401 -> "Session expired. Please login again."
            404 -> "Content not found"
            500 -> "Server error. Please try again later."
            else -> "Something went wrong (${throwable.code()})"
        }
        is FirebaseFirestoreException -> "Database error. Please try again."
        else -> throwable.message ?: "Unknown error occurred"
    }
}

PART 11: COMPLETE SCREEN PROMPT LIST

11.1 AI Prompts for Each Screen
Use these exact prompts with any AI coding assistant to generate each screen:

PROMPT — SCREEN 1 (Splash):
text
Create SplashActivity.kt and activity_splash.xml for the Shilpa-Kala Showcase Android app (Kotlin). 
The splash screen should: show app name "Shilpa-Kala Showcase" in Heritage Gold (#D4A843) on deep brown 
(#3E2723) gradient background, display a Lottie animation loader at bottom, last 2.5 seconds, then check 
FirebaseAuth.currentUser — if null check DataStore for onboarding status (navigate to OnboardingActivity 
if not done, else RoleSelectionActivity), if logged in check user role from DataStore and navigate to 
BuyerMainActivity or SellerMainActivity accordingly. Use ViewBinding. Full screen mode enabled.
PROMPT — SCREEN 2 (Onboarding):
text
Create OnboardingActivity.kt and activity_onboarding.xml for Shilpa-Kala Showcase Android app (Kotlin).
3-page ViewPager2 carousel. Page 1: "Discover Ancient Indian Crafts" with illustration. Page 2: "Work 
in Progress — Witnessed Live". Page 3: "Negotiate Directly with the Shilpi". Each page has illustration 
image, title (24sp bold heritage brown), subtitle (14sp grey). Bottom bar has dot indicators, Skip button 
(top right), Next button (changes to "Get Started" on page 3). On complete/skip: save onboarding done 
to DataStore Preferences, navigate to RoleSelectionActivity. Use ViewBinding, Material Design 3.
PROMPT — SCREEN 3 (Role Selection):
text
Create RoleSelectionActivity.kt and activity_role_selection.xml for Shilpa-Kala Showcase (Kotlin).
Background: cream (#FDF8F0). Two large clickable cards: Card 1 "I am a Buyer" (dark brown #3E2723 
background, white text), Card 2 "I am a Shilpi" (gold #D4A843 background, dark text). Selected card 
gets gold border and scale animation. Continue button disabled until selection made. On continue: 
start PhoneAuthActivity with Intent extra "USER_ROLE" = "buyer" or "seller". Use ViewBinding, 
Material Design 3, spring animations on card selection.
PROMPT — SCREEN 4 (Phone Auth):
text
Create PhoneAuthActivity.kt and activity_phone_auth.xml for Shilpa-Kala Showcase (Kotlin).
Receives "USER_ROLE" from Intent. Shows phone number input with +91 country code prefix, validation 
(10 digits), "Send OTP" MaterialButton. Uses Firebase PhoneAuthProvider to send OTP. On success 
navigate to OtpVerificationActivity passing VERIFICATION_ID, PHONE_NUMBER, USER_ROLE as Intent extras.
Show loading state during verification. Show error Toast on failure. Uses ViewBinding.
PROMPT — SCREEN 5 (OTP Verify):
text
Create OtpVerificationActivity.kt and activity_otp_verification.xml for Shilpa-Kala Showcase (Kotlin).
6 individual EditText boxes for OTP input with auto-advance and auto-backspace behavior. 60-second 
countdown timer with "Resend OTP" becoming active after 0. Receives VERIFICATION_ID, PHONE_NUMBER, 
USER_ROLE from Intent. On verify: use PhoneAuthProvider.getCredential then signInWithCredential. 
If new user: create User document in Firestore collection "users" with uid, phone, role, createdAt. 
Save uid and role to DataStore. Navigate to BuyerMainActivity or SellerMainActivity with clearTask flag.
Show Lottie success animation before navigation. Uses ViewBinding.
PROMPT — SCREEN 6 (Buyer Main):
text
Create BuyerMainActivity.kt and activity_buyer_main.xml for Shilpa-Kala Showcase (Kotlin).
Bottom Navigation with 5 tabs: Home, Gallery, Search, Saved, Profile using Navigation Component 
with NavHostFragment. Active tab color #D4A843. Uses Navigation graph nav_buyer.xml. Handles 
back press to return to Home tab if not already there. Top AppBar shown on Home and Gallery tabs. 
Uses ViewBinding, MaterialBottomNavigationView.
PROMPT — SCREEN 7 (Buyer Home):
text
Create BuyerHomeFragment.kt, fragment_buyer_home.xml, and BuyerHomeViewModel.kt for Shilpa-Kala 
Showcase (Kotlin). NestedScrollView with: (1) ViewPager2 hero banner auto-scrolling featured products 
loaded from Firestore "products" where isActive=true, (2) Horizontal RecyclerView of carving style chips 
(Hoysala/Dravidian/Chola/Modern/Wood), (3) 2-column grid RecyclerView of featured products with Glide 
image loading using DiskCacheStrategy.ALL, shimmer placeholder, showing title/price/rating/availability, 
(4) Horizontal RecyclerView of top sellers from Firestore "sellers", (5) Horizontal RecyclerView of 
heritage stories from "heritage_stories". ViewModel uses StateFlow and viewModelScope coroutines. 
Navigate to ProductDetailActivity on product tap passing PRODUCT_ID extra.
PROMPT — SCREEN 8 (Gallery):
text
Create GalleryFragment.kt, fragment_gallery.xml, GalleryViewModel.kt for Shilpa-Kala Showcase (Kotlin).
StaggeredGridLayoutManager 2-column RecyclerView of all products. Horizontal filter chip row (All/Hoysala/
Dravidian/Chola/Black Granite/Available). Sort bottom sheet dialog. Filter bottom sheet with material chips, 
style chips, price range RangeSlider (0 to 1000000), availability toggle. Firestore query with filters 
applied. Pagination: load 20 items, detect RecyclerView scroll to bottom to load more using lastDocument. 
Shimmer loading effect. Empty state view. Tap product → ProductDetailActivity with PRODUCT_ID extra.
PROMPT — SCREEN 9 (Product Detail):
text
Create ProductDetailActivity.kt, activity_product_detail.xml, ProductDetailViewModel.kt for Shilpa-Kala 
Showcase (Kotlin). Receives PRODUCT_ID extra. Fetches Product from Firestore "products" and Seller from 
"sellers". Layout: ViewPager2 image gallery (Glide high-res, tap → FullScreenImageActivity with IMAGE_URLS 
and POSITION), back+bookmark+share buttons, product title/ID/price/availability, quick specs row 
(material/style/weight/dimensions), stone freshness indicator, expandable description, seller card with 
CircleImageView + verified badge + "View Portfolio" button, work-in-progress preview with "See Full Timeline" 
button, ratings summary + top 2 reviews, heritage story teaser. Fixed bottom bar with price and "Enquire on 
WhatsApp" button (#25D366). WhatsApp button uses Intent.ACTION_VIEW with wa.me URI containing pre-filled 
message including Product ID, product name, price, material. Bookmark functionality saves to Room DB. 
Uses ViewBinding, MVVM.
PROMPT — SCREEN 10 (Fullscreen Image):
text
Create FullScreenImageActivity.kt and activity_fullscreen_image.xml for Shilpa-Kala Showcase (Kotlin).
Receives IMAGE_URLS (ArrayList<String>) and POSITION (Int) from Intent. Full immersive mode (hide status 
and nav bars). PhotoView from com.github.chrisbanes:PhotoView for pinch-to-zoom (1x to 5x). ViewPager2 
for swiping between images. Overlay (visible on single tap, auto-hides after 3 sec): top has back X 
button and counter "3/7", bottom has thumbnail RecyclerView strip. Glide loads each image with 
DiskCacheStrategy.ALL. Swipe down to close. Black background.
PROMPT — SCREEN 11 (Work in Progress):
text
Create WorkInProgressActivity.kt, activity_work_in_progress.xml, TimelineAdapter.kt for Shilpa-Kala 
Showcase (Kotlin). Receives PRODUCT_ID extra. Fetches timeline entries from Firestore subcollection 
"timeline/{productId}/entries" ordered by stageNumber. Vertical RecyclerView with custom timeline layout: 
left column has colored numbered circle + connecting vertical line, right column has date + stage name + 
description + Glide-loaded image (tap → FullScreenImageActivity). Header shows product thumbnail and name. 
Empty state if no timeline entries. Stage circles use different colors per stage number.
PROMPT — SCREEN 12 (Heritage Story):
text
Create HeritageStoryActivity.kt and activity_heritage_story.xml for Shilpa-Kala Showcase (Kotlin).
Receives STORY_ID or STYLE_NAME extra. Fetches from Firestore "heritage_stories". Scrollable article layout: 
hero image with gradient overlay and style name, expandable sections (Origins/Features/Location), styled 
quote card with seller quote (gold left border, italic text, serif font), horizontal RecyclerView of related 
products (tap → ProductDetailActivity), share button using Android ShareCompat. Uses ViewBinding.
PROMPT — SCREEN 13 (Search):
text
Create SearchFragment.kt, fragment_search.xml, SearchViewModel.kt for Shilpa-Kala Showcase (Kotlin).
SearchView with real-time search using StateFlow debounce (300ms). Shows recent searches (from DataStore 
list) and popular search chips when query empty. On search: query Firestore products where title contains 
query (case-insensitive using whereGreaterThanOrEqualTo workaround). Filter panel with material/style chips 
and price RangeSlider. Results in 2-column grid. Count text "34 sculptures found". Empty state illustration. 
Save recent search terms to DataStore. Tap result → ProductDetailActivity.
PROMPT — SCREEN 14 (Saved):
text
Create SavedFragment.kt, fragment_saved.xml, SavedViewModel.kt for Shilpa-Kala Showcase (Kotlin).
Uses Room Database with BookmarkEntity (productId, title, primaryImageUrl, price, material, savedAt). 
BookmarkDao with getAllBookmarks() as LiveData. Displays saved items in 2-column RecyclerView grid. 
Swipe-to-delete with ItemTouchHelper showing red delete background. Empty state with "Explore Gallery" 
button navigating to Gallery tab. Sort options (recently saved, price). Observe LiveData from ViewModel. 
Tap item → ProductDetailActivity.
PROMPT — SCREEN 15 (Buyer Profile):
text
Create BuyerProfileFragment.kt, fragment_buyer_profile.xml, BuyerProfileViewModel.kt for Shilpa-Kala 
Showcase (Kotlin). Shows buyer profile with CircleImageView (edit photo), name, phone (masked), stats row 
(saved count from Room, inquiries count). Settings sections: Edit Profile (inline edit mode), language toggle 
EN/KN (saves to DataStore), notification settings, My Reviews list, About app, Terms link. Logout button with 
confirmation AlertDialog — on confirm: FirebaseAuth.signOut(), clear DataStore, navigate to RoleSelectionActivity 
with clearTask flags.
PROMPT — SCREEN 16 (Seller Main):
text
Create SellerMainActivity.kt and activity_seller_main.xml for Shilpa-Kala Showcase (Kotlin). Bottom 
Navigation with 4 tabs: Dashboard, Portfolio, Add (+ FAB style highlighted tab), Profile. Navigation 
Component with nav_seller.xml NavGraph. Active tab gold color. Seller badge in top bar. ViewBinding.
PROMPT — SCREEN 17 (Seller Dashboard):
text
Create SellerDashboardFragment.kt, fragment_seller_dashboard.xml, SellerDashboardViewModel.kt for 
Shilpa-Kala Showcase (Kotlin). Fetches seller data from Firestore "sellers/{uid}" and their products 
from "products" where sellerId equals current uid. Shows greeting with seller name, horizontal scroll 
of stats cards (Total Products count, Total Views sum, Total Inquiries sum, Average Rating). LineChart 
using MPAndroidChart for inquiry trends last 30 days. Recent inquiries list. Quick action buttons 
"Add Product" and "View Portfolio". Verification status card if not verified. Uses StateFlow.
PROMPT — SCREEN 18 (Seller Portfolio):
text
Create PortfolioFragment.kt, fragment_portfolio.xml, PortfolioViewModel.kt for Shilpa-Kala Showcase 
(Kotlin). Shows seller's own products from Firestore "products" where sellerId=currentUid. Tab filter: 
All/Available/Sold/Inactive. 2-column RecyclerView grid with product cards showing image/name/price/status 
chip/view count/inquiry count. 3-dot popup menu per card: Edit (→ AddProductActivity with PRODUCT_ID), 
Deactivate (toggle isActive), Delete (confirm dialog then Firestore delete), Add Timeline (→ AddTimelineActivity 
with PRODUCT_ID). FAB gold + navigates to AddProductActivity. ViewBinding, MVVM.
PROMPT — SCREEN 19 (Add Product):
text
Create AddProductActivity.kt, activity_add_product.xml, AddProductViewModel.kt for Shilpa-Kala Showcase 
(Kotlin). Multi-section NestedScrollView form. Image picker section: grid showing selected images (up to 10) 
from gallery/camera using ActivityResultLauncher, each with remove button. Text fields: product title, price 
(number input with ₹ prefix), dimensions, weight. Dropdowns/chips: material (Black Granite/Sandstone/Marble/
Limestone/Wood), carving style (Hoysala/Dravidian/Chola/Traditional/Modern), availability dropdown, stone 
freshness radio, description TextArea (min 100 chars). On publish: show progress dialog, upload each image 
to Firebase Storage at "products/{sellerId}/{uuid}.jpg", collect download URLs, generate product ID format 
"SKS-{year}-{5digits}", save Product to Firestore with all fields. On success navigate to ProductDetailActivity. 
Edit mode: if PRODUCT_ID extra received, pre-fill all fields. ViewBinding, MVVM.
PROMPT — SCREEN 20 (Add Timeline):
text
Create AddTimelineActivity.kt, activity_add_timeline.xml, AddTimelineViewModel.kt for Shilpa-Kala Showcase 
(Kotlin). Receives PRODUCT_ID extra. Shows product reference card at top. Form: stage name dropdown 
(predefined stages: Raw Stone Selection/Initial Block Shaping/Base Proportioning/Rough Carving/Facial Features/
Detail Work/Smoothing & Polishing/Final Inspection + Custom), single image picker for stage photo, description 
TextArea (min 50 chars), DatePicker defaulting to today, hours spent number input. On save: upload image to 
Firebase Storage, save TimelineEntry to Firestore subcollection "timeline/{productId}/entries" with auto-generated 
ID, stageNumber, timestamp. On success navigate to WorkInProgressActivity with PRODUCT_ID.
PROMPT — SCREEN 21 (Seller Profile):
text
Create SellerProfileFragment.kt, fragment_seller_profile.xml, SellerProfileViewModel.kt for Shilpa-Kala 
Showcase (Kotlin). Fetches seller from Firestore "sellers/{uid}". Shows profile card (hero gradient header, 
CircleImageView with camera edit button, name, verified badge if isVerifiedArtisan, village, specialty, 
rating). Profile completion progress bar. Editable fields: full name (Kannada + English), village, specialty, 
bio (500 chars), carving styles multi-select chips, years experience, materials chips, WhatsApp number. 
Verification status card with apply button if not verified. "Update Profile" saves to Firestore. 
"Preview as Buyer" navigates to SellerPortfolioActivity. Logout with confirmation → RoleSelectionActivity.
PROMPT — SCREEN 22 (Seller Public Portfolio / Buyer View):
text
Create SellerPortfolioActivity.kt and activity_seller_portfolio.xml for Shilpa-Kala Showcase (Kotlin).
Receives SELLER_ID extra. Fetches Seller from "sellers/{sellerId}" and their products from "products" 
where sellerId matches. CollapsingToolbarLayout with hero image. Overlapping seller card with CircleImageView, 
verified badge, name, village, specialty, experience, bio (expandable), rating. Stats row (sculpture count, 
avg rating, buyer count). WhatsApp enquiry button opens wa.me with general inquiry message. Product grid 
(all active products). Heritage specialty card. Tap product → ProductDetailActivity. Uses ViewBinding.
PROMPT — SCREEN 23 (Reviews):
text
Create ReviewsActivity.kt, activity_reviews.xml, ReviewsViewModel.kt for Shilpa-Kala Showcase (Kotlin).
Receives PRODUCT_ID extra. Fetches reviews from Firestore subcollection "reviews/{productId}/entries" 
ordered by createdAt desc. Summary card: large rating number, 5 stars, total count, rating breakdown 
horizontal bars (5★ through 1★ with percentages). Sort options. Reviews RecyclerView: each item has 
CircleImageView (Glide), buyer name (anonymized), star rating, date, review text, verified buyer chip if 
applicable, helpful count. "Write a Review" button (only if role=buyer): opens ModalBottomSheet with 
5 interactive stars, review TextArea, submit button — saves Review to Firestore subcollection. Update 
product's aggregate rating in Firestore. ViewBinding, MVVM.

PART 12: BUILD & DEPLOYMENT CHECKLIST

12.1 Pre-Development Setup
text
□ 1. Install Android Studio Hedgehog or newer
□ 2. Create new project: Empty Activity, Kotlin, Package: com.shilpakala.showcase, Min SDK: 26
□ 3. Create Firebase project, add Android app, download google-services.json
□ 4. Place google-services.json in /app directory
□ 5. Add all dependencies to build.gradle.kts
□ 6. Add google-services plugin to project-level build.gradle
□ 7. Enable Firebase Phone Auth in Firebase Console
□ 8. Enable Firestore in Firebase Console
□ 9. Enable Firebase Storage in Firebase Console
□ 10. Create Firestore collections: users, sellers, products, timeline, reviews, heritage_stories
□ 11. Add SHA-1 fingerprint to Firebase (for Phone Auth to work on device)
    → Run: ./gradlew signingReport in Android Studio terminal
□ 12. Add sample data to Firestore for testing
□ 13. Create /res/font folder and add font files
□ 14. Create color and style resources
□ 15. Create navigation graphs (nav_buyer.xml, nav_seller.xml)
12.2 Development Order (Recommended Sequence)
text
PHASE 1 — Foundation (Week 1):
1. Create all data model classes
2. Set up MVVM structure
3. Configure Firebase, Room DB
4. Create Constants, Extensions, Utilities
5. Build SplashActivity

PHASE 2 — Authentication (Week 1-2):
6. OnboardingActivity
7. RoleSelectionActivity
8. PhoneAuthActivity
9. OtpVerificationActivity

PHASE 3 — Buyer Core (Week 2-3):
10. BuyerMainActivity (navigation setup)
11. BuyerHomeFragment
12. GalleryFragment
13. ProductDetailActivity (MOST IMPORTANT)
14. FullScreenImageActivity

PHASE 4 — Buyer Secondary (Week 3):
15. WorkInProgressActivity
16. HeritageStoryActivity
17. SearchFragment
18. SavedFragment
19. BuyerProfileFragment

PHASE 5 — Seller Core (Week 4):
20. SellerMainActivity
21. SellerDashboardFragment
22. PortfolioFragment
23. AddProductActivity

PHASE 6 — Seller Secondary (Week 4-5):
24. AddTimelineActivity
25. SellerProfileFragment
26. SellerPortfolioActivity (public view)
27. ReviewsActivity

PHASE 7 — Polish (Week 5):
28. Shimmer loading effects
29. Error states and empty states
30. Animations and transitions
31. Bilingual support (Kannada strings)
32. Performance optimization
33. Testing
12.3 Testing Checklist
text
Authentication:
□ OTP received on real device
□ New user creates profile in Firestore
□ Existing user session persists
□ Logout clears session

Buyer Flow:
□ Home screen loads all sections
□ Gallery loads with pagination
□ Product detail shows all info
□ Images load and zoom works
□ WhatsApp opens with correct pre-filled message including Product ID
□ Bookmarks save and persist
□ Search returns relevant results
□ Filters work correctly

Seller Flow:
□ Dashboard shows correct stats
□ Add product uploads images to Storage
□ Product appears in portfolio after creation
□ Product ID generated in SKS-YYYY-NNNNN format
□ Timeline entries appear in correct order
□ Profile updates save to Firestore

Performance:
□ Cold start under 3 seconds
□ Images load under 1.5 seconds on 4G
□ No memory crashes with 20+ images
□ Offline — previously cached images load
□ Scroll smooth at 55+ FPS

PART 13: BILINGUAL SUPPORT

13.1 Strings Configuration (strings.xml)
XML
<!-- res/values/strings.xml (English) -->
<resources>
    <string name="app_name">Shilpa-Kala Showcase</string>
    <string name="app_tagline">Preserving India\'s Stone Carving Heritage</string>
    <string name="btn_send_otp">Send OTP</string>
    <string name="btn_verify">Verify &amp; Continue</string>
    <string name="btn_enquire_whatsapp">Enquire on WhatsApp</string>
    <string name="btn_see_timeline">See Full Timeline</string>
    <string name="btn_view_portfolio">View Portfolio</string>
    <string name="label_verified_artisan">Verified Artisan</string>
    <string name="label_stone_freshness">Stone Freshness</string>
    <string name="label_product_id">Product ID</string>
    <string name="label_carving_style">Carving Style</string>
    <string name="label_material">Material</string>
    <string name="label_price">Price</string>
    <string name="nav_home">Home</string>
    <string name="nav_gallery">Gallery</string>
    <string name="nav_search">Search</string>
    <string name="nav_saved">Saved</string>
    <string name="nav_profile">Profile</string>
    <string name="role_buyer">I am a Buyer</string>
    <string name="role_seller">I am a Shilpi</string>
</resources>

<!-- res/values-kn/strings.xml (Kannada) -->
<resources>
    <string name="app_name">ಶಿಲ್ಪ-ಕಲಾ ಪ್ರದರ್ಶನ</string>
    <string name="app_tagline">ಭಾರತದ ಶಿಲಾ ಕೆತ್ತನೆ ಪರಂಪರೆಯ ಸಂರಕ್ಷಣೆ</string>
    <string name="btn_send_otp">OTP ಕಳುಹಿಸಿ</string>
    <string name="btn_verify">ಪರಿಶೀಲಿಸಿ ಮುಂದುವರಿಯಿರಿ</string>
    <string name="btn_enquire_whatsapp">WhatsApp ನಲ್ಲಿ ವಿಚಾರಿಸಿ</string>
    <string name="label_verified_artisan">ಪರಿಶೀಲಿತ ಕಲಾವಿದ</string>
    <string name="label_stone_freshness">ಕಲ್ಲಿನ ತಾಜಾತನ</string>
    <string name="nav_home">ಮುಖಪುಟ</string>
    <string name="nav_gallery">ಗ್ಯಾಲರಿ</string>
    <string name="nav_search">ಹುಡುಕಿ</string>
    <string name="nav_saved">ಉಳಿಸಿದವು</string>
    <string name="nav_profile">ಪ್ರೊಫೈಲ್</string>
    <string name="role_buyer">ನಾನು ಖರೀದಿದಾರ</string>
    <string name="role_seller">ನಾನು ಶಿಲ್ಪಿ</string>
</resources>

PART 14: SUMMARY REFERENCE CARD

Quick Reference — All 23 Screens
#Screen IDNameFileRole1SCR-001SplashSplashActivityBoth2SCR-002OnboardingOnboardingActivityBoth3SCR-003Role SelectionRoleSelectionActivityBoth4SCR-004Phone AuthPhoneAuthActivityBoth5SCR-005OTP VerifyOtpVerificationActivityBoth6SCR-006Buyer Main HostBuyerMainActivityBuyer7SCR-007Buyer HomeBuyerHomeFragmentBuyer8SCR-008GalleryGalleryFragmentBuyer9SCR-009Product DetailProductDetailActivityBuyer10SCR-010Full Screen ImageFullScreenImageActivityBuyer11SCR-011Work in ProgressWorkInProgressActivityBuyer12SCR-012Heritage StoryHeritageStoryActivityBuyer13SCR-013SearchSearchFragmentBuyer14SCR-014Saved / BookmarksSavedFragmentBuyer15SCR-015Buyer ProfileBuyerProfileFragmentBuyer16SCR-016Seller Main HostSellerMainActivitySeller17SCR-017Seller DashboardSellerDashboardFragmentSeller18SCR-018Seller PortfolioPortfolioFragmentSeller19SCR-019Add ProductAddProductActivitySeller20SCR-020Add TimelineAddTimelineActivitySeller21SCR-021Seller ProfileSellerProfileFragmentSeller22SCR-022Public PortfolioSellerPortfolioActivityBoth23SCR-023ReviewsReviewsActivityBoth
Tech Stack Summary
LayerTechnologyLanguageKotlin 100%IDEAndroid Studio Hedgehog+Min SDKAPI 26 (Android 8.0)ArchitectureMVVM + Repository PatternBackendFirebase (Auth + Firestore + Storage)Image LoadingGlide 4.x with DiskCacheStrategy.ALLPinch ZoomPhotoView libraryLocal DBRoom Persistence LibraryNetworkingRetrofit 2 + OkHttp 3AsyncKotlin Coroutines + FlowNavigationNavigation ComponentUIMaterial Design 3 + Custom Heritage PaletteAnimationsLottie + Material AnimationsLoadingFacebook ShimmerAnalyticsMPAndroidChart (seller stats)ChartsMPAndroidChartPreferencesDataStore PreferencesDimensionsSDP + SSP libraries
Document Version: 1.0 | Shilpa-Kala Showcase | 2025
This SOP is the complete specification for building the Shilpa-Kala Showcase Android application. All screens, data models, navigation flows, backend configurations, and implementation code are included above. Hand this document directly to your AI coding assistant or development team.
 
Top of Form
Bottom of Form

