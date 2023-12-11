package com.bilgeadam.OdevFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/*
    isimlistesi.txt dosyamız olacak
    bu dosyayı okuyp baska bir dosyaya yazacagız  secilecekler.txt dosyasına
    daha sonra islemler yapıp birtane veri secip bu veri secilmisler.txt dosyasına ekleyeceğiz

    bu 3 dosya isçin bir sabitler sınıfı olusturalım

    OgrenciFileISlemler sınfımız olsun bu sınıfda bir ogrenci listesi tutalım
    1- dosyadan veri okuma ==> dosyadan okudugumuz(isimlistesi.txt) veriyi listeye ekleyeceğiz
    2-ogrenci listemizdeki verileri secilecekler.txt dosyasına yazdıracagız;
    3-ogrenci sec metodu yazalım==> buda listemizden rastgele ogrenci secmek için bir index degeri donsun
    4-secilen ogrenciyi  secilmisler.txt dosyasına yazdıralım ve aynı zmanda secilecler.txt de dosyasından cıkartalım
    rastgele ogrenci secilecek rastgele ogrenci sec metodu ile beraber gelen indexden daha sonra bu veri secilmisler.txt aktarılacak
    secilecekler.txt guncellenecek

 */

public class OgrenciFileIslemler {
    private List<String> ogrenciListesi = new ArrayList<>();

    public void dosyadanVeriOku() {
        try (BufferedReader reader = new BufferedReader(new FileReader(Sabitler.ISIM_LISTESI_DOSYASI))) {
            String satir;
            while ((satir = reader.readLine()) != null) {
                ogrenciListesi.add(satir);
            }
        } catch (IOException e) {
            System.out.println("Dosyadan veri okuma hatası: " + e.getMessage());
        }
    }
    public void ogrenciListesiniDosyayaYaz() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Sabitler.SECILECEKLER_DOSYASI))) {
            for (String ogrenci : ogrenciListesi) {
                writer.write(ogrenci + "\n");
            }
        } catch (IOException e) {
            System.out.println("Dosyaya yazma hatası: " + e.getMessage());
        }
    }
    public void ogrenciSec() {
        if (ogrenciListesi.isEmpty()) {
            System.out.println("Öğrenci listesi boş.");
        }
        int secilenIndex=0;
        try {
            Random random = new Random();
            secilenIndex = random.nextInt(ogrenciListesi.size());
        }catch (IllegalArgumentException e){
            System.out.println(e.toString());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(Sabitler.SECILMISLER_DOSYASI, true));
             BufferedWriter secileceklerWriter = new BufferedWriter(new FileWriter(Sabitler.SECILECEKLER_DOSYASI))) {

            String secilenOgrenci=ogrenciListesi.get(secilenIndex);
            // Secilen ogrenciyi secilmisler.txt dosyasina yaz
            writer.write(secilenOgrenci + "\n");

            // Secilecekler.txt dosyasindan secilen ogrenciyi cikar
            for (String ogrenci : ogrenciListesi) {
                if (!ogrenci.equals(secilenOgrenci)) {
                    secileceklerWriter.write(ogrenci + "\n");
                }
            }

        } catch (IOException e) {
            System.out.println("Dosyaya yazma hatası: " + e.getMessage());
        }
    }
    public void secilmisleriOkuVeYaz() {
        try (BufferedReader reader = new BufferedReader(new FileReader(Sabitler.SECILMISLER_DOSYASI))) {
            System.out.println("Seçilmişler Dosyası İçeriği:");

            String satir;
            while ((satir = reader.readLine()) != null) {
                System.out.println(satir);
            }
        } catch (IOException e) {
            System.out.println("Seçilmişler dosyasını okuma hatası: " + e.getMessage());
        }
    }




    public static void main(String[] args) {
        OgrenciFileIslemler ogrenciIslemler = new OgrenciFileIslemler();
        ogrenciIslemler.dosyadanVeriOku();
        ogrenciIslemler.ogrenciListesiniDosyayaYaz();
        ogrenciIslemler.ogrenciSec();
        ogrenciIslemler.secilmisleriOkuVeYaz();

    }

    public List<String> getOgrenciListesi() {
        return ogrenciListesi;
    }

    public void setOgrenciListesi(List<String> ogrenciListesi) {
        this.ogrenciListesi = ogrenciListesi;
    }
}
