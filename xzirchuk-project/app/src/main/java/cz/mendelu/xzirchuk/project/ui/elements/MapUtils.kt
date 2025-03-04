package cz.mendelu.xzirchuk.project.ui.elements

object MapStyle {
    val json_dark = "[\n" +
            "    {\n" +
            "        \"featureType\": \"all\",\n" +
            "        \"elementType\": \"labels.text.fill\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"saturation\": 36\n" +
            "            },\n" +
            "            {\n" +
            "                \"color\": \"#000000\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"lightness\": 40\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"all\",\n" +
            "        \"elementType\": \"labels.text.stroke\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"on\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"color\": \"#000000\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"lightness\": 16\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"all\",\n" +
            "        \"elementType\": \"labels.icon\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"off\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"administrative\",\n" +
            "        \"elementType\": \"geometry.fill\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"color\": \"#000000\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"lightness\": 20\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"administrative\",\n" +
            "        \"elementType\": \"geometry.stroke\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"color\": \"#000000\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"lightness\": 17\n" +
            "            },\n" +
            "            {\n" +
            "                \"weight\": 1.2\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"administrative\",\n" +
            "        \"elementType\": \"labels\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"off\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"administrative.country\",\n" +
            "        \"elementType\": \"all\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"simplified\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"administrative.country\",\n" +
            "        \"elementType\": \"geometry\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"simplified\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"administrative.country\",\n" +
            "        \"elementType\": \"labels.text\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"simplified\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"administrative.province\",\n" +
            "        \"elementType\": \"all\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"off\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"administrative.locality\",\n" +
            "        \"elementType\": \"all\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"simplified\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"saturation\": \"-100\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"lightness\": \"30\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"administrative.neighborhood\",\n" +
            "        \"elementType\": \"all\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"off\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"administrative.land_parcel\",\n" +
            "        \"elementType\": \"all\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"off\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"landscape\",\n" +
            "        \"elementType\": \"all\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"simplified\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"gamma\": \"0.00\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"lightness\": \"74\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"landscape\",\n" +
            "        \"elementType\": \"geometry\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"color\": \"#34303D\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"lightness\": \"-37\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"landscape.man_made\",\n" +
            "        \"elementType\": \"all\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"lightness\": \"3\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"poi\",\n" +
            "        \"elementType\": \"all\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"off\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"poi\",\n" +
            "        \"elementType\": \"geometry\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"color\": \"#000000\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"lightness\": 21\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"road\",\n" +
            "        \"elementType\": \"geometry\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"simplified\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"road.highway\",\n" +
            "        \"elementType\": \"geometry.fill\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"color\": \"#504D61\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"lightness\": \"0\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"road.highway\",\n" +
            "        \"elementType\": \"geometry.stroke\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"color\": \"#000000\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"lightness\": 29\n" +
            "            },\n" +
            "            {\n" +
            "                \"weight\": 0.2\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"road.highway\",\n" +
            "        \"elementType\": \"labels.text.fill\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"color\": \"#7d7c9b\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"lightness\": \"43\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"road.highway\",\n" +
            "        \"elementType\": \"labels.text.stroke\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"off\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"road.arterial\",\n" +
            "        \"elementType\": \"geometry\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"color\": \"#2d2c45\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"lightness\": \"1\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"road.arterial\",\n" +
            "        \"elementType\": \"labels.text\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"on\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"road.arterial\",\n" +
            "        \"elementType\": \"labels.text.fill\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"color\": \"#7d7c9b\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"road.arterial\",\n" +
            "        \"elementType\": \"labels.text.stroke\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"off\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"road.local\",\n" +
            "        \"elementType\": \"geometry\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"color\": \"#2d2c45\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"lightness\": \"-1\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"gamma\": \"1\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"road.local\",\n" +
            "        \"elementType\": \"labels.text\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"on\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"hue\": \"#ff0000\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"road.local\",\n" +
            "        \"elementType\": \"labels.text.fill\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"color\": \"#7d7c9b\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"lightness\": \"-31\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"road.local\",\n" +
            "        \"elementType\": \"labels.text.stroke\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"off\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"transit\",\n" +
            "        \"elementType\": \"geometry\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"color\": \"#2d2c45\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"lightness\": \"-36\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"water\",\n" +
            "        \"elementType\": \"geometry\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"color\": \"#2d2c45\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"lightness\": \"0\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"gamma\": \"1\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"water\",\n" +
            "        \"elementType\": \"labels.text.stroke\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"off\"\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "]"
    val json_light ="[\n" +
            "    {\n" +
            "        \"featureType\": \"administrative\",\n" +
            "        \"elementType\": \"labels.text.fill\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"color\": \"#444444\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"landscape\",\n" +
            "        \"elementType\": \"all\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"color\": \"#f2f2f2\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"poi\",\n" +
            "        \"elementType\": \"all\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"off\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"road\",\n" +
            "        \"elementType\": \"all\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"saturation\": -100\n" +
            "            },\n" +
            "            {\n" +
            "                \"lightness\": 45\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"road.highway\",\n" +
            "        \"elementType\": \"all\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"simplified\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"road.highway\",\n" +
            "        \"elementType\": \"geometry\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"on\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"road.highway\",\n" +
            "        \"elementType\": \"geometry.fill\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"on\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"color\": \"#a69aba\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"road.highway\",\n" +
            "        \"elementType\": \"geometry.stroke\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"on\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"road.arterial\",\n" +
            "        \"elementType\": \"geometry.fill\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"on\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"color\": \"#d4d1d5\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"road.arterial\",\n" +
            "        \"elementType\": \"labels.icon\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"off\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"transit\",\n" +
            "        \"elementType\": \"all\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"visibility\": \"off\"\n" +
            "            }\n" +
            "        ]\n" +
            "    },\n" +
            "    {\n" +
            "        \"featureType\": \"water\",\n" +
            "        \"elementType\": \"all\",\n" +
            "        \"stylers\": [\n" +
            "            {\n" +
            "                \"color\": \"#d4cae3\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"visibility\": \"on\"\n" +
            "            }\n" +
            "        ]\n" +
            "    }\n" +
            "]"
}