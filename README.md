# 🛒 Hepsiburada Web Test Automation

![Java 17](https://img.shields.io/badge/Java-17-orange.svg)
![Gauge](https://img.shields.io/badge/Gauge-1.6-blue.svg)
![Selenium](https://img.shields.io/badge/Selenium-4.51.0-brightgreen.svg)
![Maven](https://img.shields.io/badge/Maven-3.9-red.svg)

Hepsiburada'da arama, satıcı filtresi, sıralama ve sepete ekleme akışını doğrulayan uçtan uca UI test otomasyonu.

## 📋 Test Senaryosu (HB-1)

1. Ana sayfa açılır, çerez onayı kabul edilir.
2. "kitap" aranır ve sonuçların listelendiği doğrulanır.
3. Satıcı filtresinden "Hepsiburada" seçilir.
4. "Çok satanlar" sıralaması seçilir, filtrenin korunduğu doğrulanır.
5. İlk ürünün adı ve fiyatı not edilip ürün sepete eklenir.
6. Sepette ürünün adı, fiyatı ve satıcısının not edilenlerle aynı olduğu doğrulanır.

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
├── specs/Scenarios/Shopping/
│   └── add_to_cart.spec            # HB-1 senaryosu
├── src/test/java/com/vrgsl/step/
│   ├── driver/                     # WebDriver yönetimi ve hook'lar
│   ├── helper/                     # Config, element ve test verisi okuyucuları
│   ├── model/                      # Locator modeli
│   ├── pages/                      # Page Object sınıfları
│   └── steps/                      # Gauge adım implementasyonları
└── src/test/resources/
    ├── element-infos/              # Sayfa bazlı JSON locator'lar
    ├── value-infos/                # Test verileri
    ├── browser-options.yaml        # Tarayıcı ve bekleme ayarları
    └── qa-web.yaml                 # Ortam URL'i
```

## 🚀 Kurulum ve Çalıştırma

Gereksinimler: JDK 17, Maven, [Gauge](https://docs.gauge.org/getting_started/installing-gauge.html) ve Google Chrome.

```bash
mvn test
```

Test raporu: `reports/html-report/index.html`
