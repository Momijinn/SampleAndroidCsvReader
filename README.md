SampleAndroidCsvReader
====
AndroidでCsvファイルを読み込むためのサンプルファイル

## Description
AndroidにてAssetsファイルにあるCSVファイルを読み込み、連想配列で返すサンプルファイル

## Demo
![screenshot_20180623-075835](https://user-images.githubusercontent.com/13119897/41802457-df8657da-76bb-11e8-95b2-d4402b0d4798.png)

## Requirement
* 動作確認環境
    * Android 8.0.0
    * Android 7.1.1


## Usage
Map<String, Map<String,String>> InputCsvFile(String csv)の関数に読み込みたいCSVファイル名をあげると、列名を取得し列名に応じて値（要素）を格納し、返却する


# Example
### サンプルのCSVファイル(myID.csv)
| ID | NAME | OVERVIEW |
|:---:|:---:|:---:|
|x01 |NMB48   |NMB48（エヌエムビー フォーティーエイト[注 1]）は、日本の女性アイドルグル～|
|x02 |MOMOLAND|MOMOLAND（モモランド）は、韓国のガールズグループ。MLDエンタ～|
|x03 |乃木坂46|乃木坂46（のぎざか フォーティーシックス、Nogizaka46）は、日本の女性アイド～|

### プログラム
```java
String TargetCsvFile = "myID.csv";
Map<String, Map<String, String>> OutData = InputCsvFile(TargetCsvFile);

Log.d("x01_NAME", OutData.get("x01").get("NAME"));
//OUTPUT: NMB48
```


## Licence
This software is released under the MIT License, see LICENSE.

## Author
[Twitter](https://twitter.com/momijinn_aka)

[Blog](http://www.autumn-color.com/)
