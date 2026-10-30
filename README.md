# 🛒 Hepsiburada Web Test Automation

![Java 17](https://img.shields.io/badge/Java-17-orange.svg)
![Gauge](https://img.shields.io/badge/Gauge-1.6-blue.svg)
![Selenium](https://img.shields.io/badge/Selenium-4.51.0-brightgreen.svg)
![Maven](https://img.shields.io/badge/Maven-3.9-red.svg)

Hepsiburada'da arama, filtreleme, sıralama ve sepete ekleme akışlarını doğrulayan uçtan uca UI test otomasyonu.

## 📋 Test Senaryoları

İki senaryo da ilk üç adımı ortak bir concept'ten alır: ana sayfa açılır, çerez onayı kabul edilir, "kitap" aranır ve sonuçların listelendiği doğrulanır.

### TC001: Satıcı filtresi ve sıralama sonrası sepete ekleme
`Tags: shopping_cart, smoke, critical, regression`

1. Satıcı filtresinden "Hepsiburada" seçilir.
2. "Çok satanlar" sıralaması seçilir, filtrenin korunduğu doğrulanır.
3. İlk ürünün adı ve fiyatı not edilip ürün listeden sepete eklenir.
4. Bildirim mesajı ve sepet sayacı doğrulanır.
5. Sepette ürünün adı, fiyatı ve satıcısının not edilenlerle aynı olduğu doğrulanır.

### TC002: En çok değerlendirilen ürünü detay sayfasından sepete ekleme
`Tags: shopping_cart, product_detail, regression`

1. Sayfa kaydırılarak en az 50 ürün yüklenir.
2. İlk 50 ürün arasından en çok değerlendirmeye sahip olan seçilir; ürün detayı yeni sekmede açılır.
3. Detay sayfasında ürünün adı ve fiyatı not edilip ürün sepete eklenir.
4. Sepet sayacı doğrulanır, onay penceresinden sepete gidilir.
5. Sepette ürünün adı ve fiyatının not edilenlerle aynı olduğu doğrulanır.

## 🛠 Kullanılan Teknolojiler

* **Java 17**
* **Gauge**: senaryo ve raporlama
* **Selenium WebDriver**: tarayıcı otomasyonu
* **AssertJ**: doğrulamalar
* **Gson / SnakeYAML**: JSON ve YAML okuma
* **Maven**: bağımlılık yönetimi ve çalıştırma

## 🏗 Proje Yapısı

```text
hepsiburada-test/
├── specs/
│   ├── Concepts/Search/
│   │   └── search.cpt              # Ana sayfayı açıp arama yapan ortak adımlar
│   └── Scenarios/Shopping/
│       └── add_to_cart.spec        # TC001 ve TC002
├── src/test/java/com/vrgsl/step/
│   ├── driver/                     # WebDriver yönetimi ve hook'lar
│   ├── helper/                     # Config, element ve test verisi okuyucuları
│   ├── model/                      # Locator modeli
│   ├── pages/                      # Page Object sınıfları
│   │   ├── cart/                   # Sepet sayfası
│   │   ├── common/                 # Her sayfada bulunan header
│   │   ├── home/                   # Ana sayfa
│   │   ├── product/                # Ürün detay sayfası
│   │   └── search/                 # Arama sonuçları ve ürün kartı
│   └── steps/                      # Gauge adım implementasyonları
└── src/test/resources/
    ├── element-infos/              # Sayfa bazlı JSON locator'lar (ağırlıklı CSS)
    ├── value-infos/                # Test verileri
    ├── browser-options.yaml        # Tarayıcı ve bekleme ayarları
    └── qa-web.yaml                 # Ortam URL'i
```

## 🚀 Kurulum ve Çalıştırma

Gereksinimler: JDK 17, Maven, [Gauge](https://docs.gauge.org/getting_started/installing-gauge.html) ve Google Chrome.

```bash
# Tüm senaryolar
mvn test

# Tag ile filtreleme
mvn test -Dtags=smoke
mvn test -Dtags=product_detail
```

Test raporu: `reports/html-report/index.html`

Site headless tarayıcıyı güvenlik sayfasına yönlendirdiği için testler görünür (headed) Chrome ile çalışır.
