package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Builder tasarım deseni
class FileDataReaderBuilder {
    private String dosyaYolu;

    public FileDataReaderBuilder setDosyaYolu(String dosyaYolu) {
        this.dosyaYolu = dosyaYolu;
        return this;
    }

    public FileDataReader build() {
        return new FileDataReader(this.dosyaYolu);
    }
}

// Okuma işlemini gerçekleştiren sınıf
class FileDataReader {
    private BufferedReader bufferedReader;

    public FileDataReader(String dosyaYolu) {
        try {
            FileReader fileReader = new FileReader(dosyaYolu);
            this.bufferedReader = new BufferedReader(fileReader);
        } catch (IOException e) {
            System.err.println("Dosya okuma sırasında bir hata oluştu: " + e.getMessage());
        }
    }

    public String readLine() throws IOException {
        if (bufferedReader != null) {
            return bufferedReader.readLine();
        }
        return null;
    }

    public void close() throws IOException {
        if (bufferedReader != null) {
            bufferedReader.close();
        }
    }
}

public class FileReadingBuilderExample {
    public static void main(String[] args) {
        // Builder kullanarak dosya okuma
        FileDataReader fileDataReader = new FileDataReaderBuilder()
                .setDosyaYolu("ornek.txt")
                .build();

        try {
            String satir;
            // Dosya sonuna kadar oku ve ekrana yazdır
            while ((satir = fileDataReader.readLine()) != null) {
                System.out.println(satir);
            }

            // Dosya okuma işlemini sonlandır
            fileDataReader.close();
        } catch (IOException e) {
            System.err.println("Dosya okuma sırasında bir hata oluştu: " + e.getMessage());
        }
    }
}
