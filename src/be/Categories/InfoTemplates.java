package be.Categories;

import java.util.ArrayList;
import java.util.HashMap;

public class InfoTemplates {
    private static HashMap<String, GeneralInfo> generalInfoHashMap;
    private static HashMap<String, HealthCondition> healthConditionHashMap;
    private static HashMap<String, FunctionalAbility> functionalAbilityHashMap;
    private static ArrayList<HealthCondition> healthConditionArrayList;
    private static ArrayList<FunctionalAbility> functionalAbilityArrayList;

    public static HashMap<String, GeneralInfo> getGeneralInfoHashMap(){
        if (generalInfoHashMap == null)
            makeGeneralInfoHashMap();
        return generalInfoHashMap;
    }

    public static HashMap<String, HealthCondition> getHealthConditionHashMap(){
        if (healthConditionHashMap == null)
            makeHealthConditionLists();
        return healthConditionHashMap;
    }

    public static HashMap<String, FunctionalAbility> getFunctionalAbilityHashMap(){
        if (functionalAbilityHashMap == null)
            makeFunctionalAbilityHashMap();
        return functionalAbilityHashMap;
    }

    public static ArrayList<HealthCondition> getHealthConditionArrayList() {
        if (healthConditionArrayList == null)
            makeHealthConditionLists();
        return healthConditionArrayList;
    }

    public static void makeGeneralInfoHashMap(){
        generalInfoHashMap = new HashMap<>();

        GeneralInfo mestring = new GeneralInfo(1,"Mestring",
                """
                        Borgerens bevidste eller ubevidste
                        håndtering af livet/sygdommen – både
                        udfordringer og muligheder.""");
        generalInfoHashMap.put(mestring.getName(), mestring);

        GeneralInfo motivation = new GeneralInfo(2, "Motivation",
                """
                        Drivkraften bag at borgeren handler på
                        en bestemt måde eller går i gang
                        med/opretholder en opgave/indsats.""");
        generalInfoHashMap.put(motivation.getName(), motivation);

        GeneralInfo ressourcer = new GeneralInfo(3, "Ressourcer",
                """
                        De fysiske eller mentale kræfter, som
                        borgerenen i et vist omfang har til
                        rådighed og kan udnytte. Fysiske kræfter
                        kan fx være i form af fysisk sundhed og
                        styrke. Mentale kræfter kan fx være i
                        form af psykisk sundhed og styrke,
                        herunder tanker og måder at forholde sig
                        til situationer og andre mennesker på.""");
        generalInfoHashMap.put(ressourcer.getName(), ressourcer);

        GeneralInfo roller = new GeneralInfo(4, "Roller",
                """
                        De roller som er særligt vigtige for
                        borgeren i forhold til familie, arbejde og
                        samfund.""");
        generalInfoHashMap.put(roller.getName(), roller);

        GeneralInfo vaner = new GeneralInfo(5, "Vaner",
                """
                        Regelmæssig adfærd som borgeren har
                        tillært gennem stadig gentagelse og
                        udførelse helt eller delvist ubevidst.
                        Vaner er fx døgnrytmen, måden at blive
                        tiltalt på, kontakt med medmennesker og
                        relationer, måde at anskue verden på.""");
        generalInfoHashMap.put(vaner.getName(), vaner);

        GeneralInfo job = new GeneralInfo(6, "Uddannelse og job",
                """
                        Nuværende eller tidligere uddannelses-
                        og/eller erhvervsmæssig baggrund.
                        Fx folkeskole, erhvervsuddannelse og
                        videregående uddannelse.""");
        generalInfoHashMap.put(job.getName(), job);

        GeneralInfo livshistorie = new GeneralInfo(7, "Livshistorie",
                """
                        En beskrivelse af borgerens oplevelse af
                        væsentlige begivenheder, interesser og
                        gøremål igennem livet.""");
        generalInfoHashMap.put(livshistorie.getName(), livshistorie);

        GeneralInfo netvaerk = new GeneralInfo(8, "Netværk",
                """
                        Personer som er tæt på borgeren, og
                        som giver praktisk og/eller
                        følelsesmæssigt støtte og omsorg
                        overfor borgeren. Netværk kan være
                        offentligt eller privat.
                        Et offentligt netværk består af personlige
                        hjælpere, sundhedspersonale og andre
                        professionelle primært omsorgsgivere.
                        Privat netværk er familie, slægtning,
                        venner og bekendtskaber.""");
        generalInfoHashMap.put(netvaerk.getName(), netvaerk);

        GeneralInfo helbred = new GeneralInfo(9, "Helbredsoplysninger",
                """
                        Helbredsoplysninger:
                        Aktuelle eller tidligere sygdomme og
                        handicap der har betydning for
                        borgerens situation.
                        Sundhedsfaglige kontakter:
                        Medarbejder eller enheder indenfor
                        sundhedsvæsenet borgeren er tilknyttet,
                        fx øjenlæge, tandlæge, fodterapeut eller
                        afdeling/ambulatorium.""");
        generalInfoHashMap.put(helbred.getName(), helbred);

        GeneralInfo help = new GeneralInfo(10, "Hjælpemidler",
                """
                        Udstyr, produkter og teknologi som
                        anvendes af borgeren i daglige
                        aktiviteter, inkl. sådanne som er tilpasset
                        eller særligt fremstillet til, implanteret i,
                        placeret på eller nær personen, som
                        anvender dem. (Inkl. almindelige
                        genstande og hjælpemidler og teknologi
                        til personlig anvendelse).""");
        generalInfoHashMap.put(help.getName(), help);

        GeneralInfo bolig = new GeneralInfo(11, "Boligens indretning",
                """
                        En beskrivelse af boligens fysiske rammer
                        og omgivelser, der har betydning for
                        borgerens hverdagsliv og funktionsevne.""");
        generalInfoHashMap.put(bolig.getName(), bolig);
    }

    public static void makeHealthConditionLists(){
        healthConditionHashMap = new HashMap<>();
        healthConditionArrayList = new ArrayList<>();

        HealthCondition healthCondition1 = new HealthCondition(1, 1, "Problemer med personlig pleje");
        healthConditionHashMap.put(healthCondition1.getName(), healthCondition1);
        healthConditionArrayList.add(healthCondition1);

        HealthCondition healthCondition2 = new HealthCondition(2, 1, "Problemer med daglige aktiviteter");
        healthConditionHashMap.put(healthCondition2.getName(), healthCondition2);
        healthConditionArrayList.add(healthCondition2);

        HealthCondition healthCondition3 = new HealthCondition(3, 2, "Problemer med mobilitet og bevægelse");
        healthConditionHashMap.put(healthCondition3.getName(), healthCondition3);
        healthConditionArrayList.add(healthCondition3);

        HealthCondition healthCondition4 = new HealthCondition(4, 3, "Problemer med væskeindtag");
        healthConditionHashMap.put(healthCondition4.getName(), healthCondition4);
        healthConditionArrayList.add(healthCondition4);

        HealthCondition healthCondition5 = new HealthCondition(5, 3, "Problemer med fødeindtag");
        healthConditionHashMap.put(healthCondition5.getName(), healthCondition5);
        healthConditionArrayList.add(healthCondition5);

        HealthCondition healthCondition6 = new HealthCondition(6, 3, "Uhensigtsmæssig vægtændring");
        healthConditionHashMap.put(healthCondition6.getName(), healthCondition6);
        healthConditionArrayList.add(healthCondition6);

        HealthCondition healthCondition7 = new HealthCondition(7, 3, "Problemer med overvægt");
        healthConditionHashMap.put(healthCondition7.getName(), healthCondition7);
        healthConditionArrayList.add(healthCondition7);

        HealthCondition healthCondition8 = new HealthCondition(8, 3, "Problemer med undervægt");
        healthConditionHashMap.put(healthCondition8.getName(), healthCondition8);
        healthConditionArrayList.add(healthCondition8);

        HealthCondition healthCondition9 = new HealthCondition(9, 4, "Problemer med kirurgisk sår");
        healthConditionHashMap.put(healthCondition9.getName(), healthCondition9);
        healthConditionArrayList.add(healthCondition9);

        HealthCondition healthCondition10 = new HealthCondition(10, 4, "Problemer med diabetisk sår");
        healthConditionHashMap.put(healthCondition10.getName(), healthCondition10);
        healthConditionArrayList.add(healthCondition10);

        HealthCondition healthCondition11 = new HealthCondition(11, 4, "Problemer med cancersår");
        healthConditionHashMap.put(healthCondition11.getName(), healthCondition11);
        healthConditionArrayList.add(healthCondition11);

        HealthCondition healthCondition12 = new HealthCondition(12, 4, "Problemer med tryksår");
        healthConditionHashMap.put(healthCondition12.getName(), healthCondition12);
        healthConditionArrayList.add(healthCondition12);

        HealthCondition healthCondition13 = new HealthCondition(13, 4, "Problemer med arterielt sår");
        healthConditionHashMap.put(healthCondition13.getName(), healthCondition13);
        healthConditionArrayList.add(healthCondition13);

        HealthCondition healthCondition14 = new HealthCondition(14, 4, "Problemer med venøst sår");
        healthConditionHashMap.put(healthCondition14.getName(), healthCondition14);
        healthConditionArrayList.add(healthCondition14);

        HealthCondition healthCondition15 = new HealthCondition(15, 4, "Problemer med blandingssår");
        healthConditionHashMap.put(healthCondition15.getName(), healthCondition15);
        healthConditionArrayList.add(healthCondition15);

        HealthCondition healthCondition16 = new HealthCondition(16, 4, "Problemer med traumesår");
        healthConditionHashMap.put(healthCondition16.getName(), healthCondition16);
        healthConditionArrayList.add(healthCondition16);

        HealthCondition healthCondition17 = new HealthCondition(17, 4, "Andre problemer med hud og slimhinder");
        healthConditionHashMap.put(healthCondition17.getName(), healthCondition17);
        healthConditionArrayList.add(healthCondition17);

        HealthCondition healthCondition18 = new HealthCondition(18, 5, "Problemer med kommunikation");
        healthConditionHashMap.put(healthCondition18.getName(), healthCondition18);
        healthConditionArrayList.add(healthCondition18);

        HealthCondition healthCondition19 = new HealthCondition(19, 6, "Problemer med socialt samvær");
        healthConditionHashMap.put(healthCondition19.getName(), healthCondition19);
        healthConditionArrayList.add(healthCondition19);

        HealthCondition healthCondition20 = new HealthCondition(20, 6, "Emotionelle problemer");
        healthConditionHashMap.put(healthCondition20.getName(), healthCondition20);
        healthConditionArrayList.add(healthCondition20);

        HealthCondition healthCondition21 = new HealthCondition(21, 6, "Problemer med misbrug");
        healthConditionHashMap.put(healthCondition21.getName(), healthCondition21);
        healthConditionArrayList.add(healthCondition21);

        HealthCondition healthCondition22 = new HealthCondition(22, 6, "Mentale problemer");
        healthConditionHashMap.put(healthCondition22.getName(), healthCondition22);
        healthConditionArrayList.add(healthCondition22);

        HealthCondition healthCondition23 = new HealthCondition(23, 7, "Respirationsproblemer");
        healthConditionHashMap.put(healthCondition23.getName(), healthCondition23);
        healthConditionArrayList.add(healthCondition23);

        HealthCondition healthCondition24 = new HealthCondition(24, 7, "Cirkulationsproblemer");
        healthConditionHashMap.put(healthCondition24.getName(), healthCondition24);
        healthConditionArrayList.add(healthCondition24);

        HealthCondition healthCondition25 = new HealthCondition(25, 8, "Problemer med seksualitet");
        healthConditionHashMap.put(healthCondition25.getName(), healthCondition25);
        healthConditionArrayList.add(healthCondition25);

        HealthCondition healthCondition26 = new HealthCondition(26, 9, "Akutte smerter");
        healthConditionHashMap.put(healthCondition26.getName(), healthCondition26);
        healthConditionArrayList.add(healthCondition26);

        HealthCondition healthCondition27 = new HealthCondition(27, 9, "Periodevise smerter");
        healthConditionHashMap.put(healthCondition27.getName(), healthCondition27);
        healthConditionArrayList.add(healthCondition27);

        HealthCondition healthCondition28 = new HealthCondition(28, 9, "Kroniske smerter");
        healthConditionHashMap.put(healthCondition28.getName(), healthCondition28);
        healthConditionArrayList.add(healthCondition28);

        HealthCondition healthCondition29 = new HealthCondition(29, 9, "Problemer med synssans");
        healthConditionHashMap.put(healthCondition29.getName(), healthCondition29);
        healthConditionArrayList.add(healthCondition29);

        HealthCondition healthCondition30 = new HealthCondition(30, 9, "Problemer med lugtesans");
        healthConditionHashMap.put(healthCondition30.getName(), healthCondition30);
        healthConditionArrayList.add(healthCondition30);

        HealthCondition healthCondition31 = new HealthCondition(31, 9, "Problemer med hørelse");
        healthConditionHashMap.put(healthCondition31.getName(), healthCondition31);
        healthConditionArrayList.add(healthCondition31);

        HealthCondition healthCondition32 = new HealthCondition(32, 9, "Problemer med smagssans");
        healthConditionHashMap.put(healthCondition32.getName(), healthCondition32);
        healthConditionArrayList.add(healthCondition32);

        HealthCondition healthCondition33 = new HealthCondition(33, 9, "Problemer med følesans");
        healthConditionHashMap.put(healthCondition33.getName(), healthCondition33);
        healthConditionArrayList.add(healthCondition33);

        HealthCondition healthCondition34 = new HealthCondition(34, 10, "Døgnrytmeproblemer");
        healthConditionHashMap.put(healthCondition34.getName(), healthCondition34);
        healthConditionArrayList.add(healthCondition34);

        HealthCondition healthCondition35 = new HealthCondition(35, 10, "Søvnproblemer");
        healthConditionHashMap.put(healthCondition35.getName(), healthCondition35);
        healthConditionArrayList.add(healthCondition35);

        HealthCondition healthCondition36 = new HealthCondition(36, 11, "Problemer med hukommelse");
        healthConditionHashMap.put(healthCondition36.getName(), healthCondition36);
        healthConditionArrayList.add(healthCondition36);

        HealthCondition healthCondition37 = new HealthCondition(37, 11, "Problemer med sygdomsindsigt");
        healthConditionHashMap.put(healthCondition37.getName(), healthCondition37);
        healthConditionArrayList.add(healthCondition37);

        HealthCondition healthCondition38 = new HealthCondition(38, 11, "Problemer med indsigt i behandlingsformål");
        healthConditionHashMap.put(healthCondition38.getName(), healthCondition38);
        healthConditionArrayList.add(healthCondition38);

        HealthCondition healthCondition39 = new HealthCondition(39, 11, "Kognitive problemer");
        healthConditionHashMap.put(healthCondition39.getName(), healthCondition39);
        healthConditionArrayList.add(healthCondition39);

        HealthCondition healthCondition40 = new HealthCondition(40, 12, "Problemer med vandladning");
        healthConditionHashMap.put(healthCondition40.getName(), healthCondition40);
        healthConditionArrayList.add(healthCondition40);

        HealthCondition healthCondition41 = new HealthCondition(41, 12, "Problemer med urininkontinens");
        healthConditionHashMap.put(healthCondition41.getName(), healthCondition41);
        healthConditionArrayList.add(healthCondition41);

        HealthCondition healthCondition42 = new HealthCondition(42, 12, "Problemer med afføringsinkontinens");
        healthConditionHashMap.put(healthCondition42.getName(), healthCondition42);
        healthConditionArrayList.add(healthCondition42);

        HealthCondition healthCondition43 = new HealthCondition(43, 12, "Problemer med mave og tarm");
        healthConditionHashMap.put(healthCondition43.getName(), healthCondition43);
        healthConditionArrayList.add(healthCondition43);
    }

    public static void makeFunctionalAbilityHashMap(){
        functionalAbilityHashMap = new HashMap<>();
        functionalAbilityArrayList = new ArrayList<>();

        FunctionalAbility functionalAbility1 = new FunctionalAbility(1, 1, "Vaske sig");
        functionalAbilityHashMap.put(functionalAbility1.getName(), functionalAbility1);
        functionalAbilityArrayList.add(functionalAbility1);

        FunctionalAbility functionalAbility2 = new FunctionalAbility(2, 1, "Gå på toilet");
        functionalAbilityHashMap.put(functionalAbility2.getName(), functionalAbility2);
        functionalAbilityArrayList.add(functionalAbility2);

        FunctionalAbility functionalAbility3 = new FunctionalAbility(3, 1, "Kropspleje");
        functionalAbilityHashMap.put(functionalAbility3.getName(), functionalAbility3);
        functionalAbilityArrayList.add(functionalAbility3);

        FunctionalAbility functionalAbility4 = new FunctionalAbility(4, 1, "Af- og påklædning");
        functionalAbilityHashMap.put(functionalAbility4.getName(), functionalAbility4);
        functionalAbilityArrayList.add(functionalAbility4);

        FunctionalAbility functionalAbility5 = new FunctionalAbility(5, 1, "Spise");
        functionalAbilityHashMap.put(functionalAbility5.getName(), functionalAbility5);
        functionalAbilityArrayList.add(functionalAbility5);

        FunctionalAbility functionalAbility6 = new FunctionalAbility(6, 1, "Drikke");
        functionalAbilityHashMap.put(functionalAbility6.getName(), functionalAbility6);
        functionalAbilityArrayList.add(functionalAbility6);

        FunctionalAbility functionalAbility7 = new FunctionalAbility(7, 1, "Varetage egen sundhed");
        functionalAbilityHashMap.put(functionalAbility7.getName(), functionalAbility7);
        functionalAbilityArrayList.add(functionalAbility7);

        FunctionalAbility functionalAbility8 = new FunctionalAbility(8, 1, "Fødeindtagelse");
        functionalAbilityHashMap.put(functionalAbility8.getName(), functionalAbility8);
        functionalAbilityArrayList.add(functionalAbility8);

        FunctionalAbility functionalAbility9 = new FunctionalAbility(9, 2, "Udføre daglige rutiner");
        functionalAbilityHashMap.put(functionalAbility9.getName(), functionalAbility9);
        functionalAbilityArrayList.add(functionalAbility9);

        FunctionalAbility functionalAbility10 = new FunctionalAbility(10, 2, "Skaffe sig varer og tjenesteydelser");
        functionalAbilityHashMap.put(functionalAbility10.getName(), functionalAbility10);
        functionalAbilityArrayList.add(functionalAbility10);

        FunctionalAbility functionalAbility11 = new FunctionalAbility(11, 2, "Lave mad");
        functionalAbilityHashMap.put(functionalAbility11.getName(), functionalAbility11);
        functionalAbilityArrayList.add(functionalAbility11);

        FunctionalAbility functionalAbility12 = new FunctionalAbility(12, 2, "Lave husligt arbejde");
        functionalAbilityHashMap.put(functionalAbility12.getName(), functionalAbility12);
        functionalAbilityArrayList.add(functionalAbility12);

        FunctionalAbility functionalAbility13 = new FunctionalAbility(13, 3, "Ændre kropsstilling");
        functionalAbilityHashMap.put(functionalAbility13.getName(), functionalAbility13);
        functionalAbilityArrayList.add(functionalAbility13);

        FunctionalAbility functionalAbility14 = new FunctionalAbility(14, 3, "Forflytte sig");
        functionalAbilityHashMap.put(functionalAbility14.getName(), functionalAbility14);
        functionalAbilityArrayList.add(functionalAbility14);

        FunctionalAbility functionalAbility15 = new FunctionalAbility(15, 3, "Løfte og bære");
        functionalAbilityHashMap.put(functionalAbility15.getName(), functionalAbility15);
        functionalAbilityArrayList.add(functionalAbility15);

        FunctionalAbility functionalAbility16 = new FunctionalAbility(16, 3, "Gå");
        functionalAbilityHashMap.put(functionalAbility16.getName(), functionalAbility16);
        functionalAbilityArrayList.add(functionalAbility16);

        FunctionalAbility functionalAbility17 = new FunctionalAbility(17, 3, "Bevæge sig omkring");
        functionalAbilityHashMap.put(functionalAbility17.getName(), functionalAbility17);
        functionalAbilityArrayList.add(functionalAbility17);

        FunctionalAbility functionalAbility18 = new FunctionalAbility(18, 3, "Færden i forskellige omgivelser");
        functionalAbilityHashMap.put(functionalAbility18.getName(), functionalAbility18);
        functionalAbilityArrayList.add(functionalAbility18);

        FunctionalAbility functionalAbility19 = new FunctionalAbility(19, 3, "Bruge transportmidler");
        functionalAbilityHashMap.put(functionalAbility19.getName(), functionalAbility19);
        functionalAbilityArrayList.add(functionalAbility19);

        FunctionalAbility functionalAbility20 = new FunctionalAbility(20, 3, "Udholdenhed");
        functionalAbilityHashMap.put(functionalAbility20.getName(), functionalAbility20);
        functionalAbilityArrayList.add(functionalAbility20);

        FunctionalAbility functionalAbility21 = new FunctionalAbility(21, 3, "Muskelstyrke");
        functionalAbilityHashMap.put(functionalAbility21.getName(), functionalAbility21);
        functionalAbilityArrayList.add(functionalAbility21);

        FunctionalAbility functionalAbility22 = new FunctionalAbility(22, 4, "Tilegne sig færdigheder");
        functionalAbilityHashMap.put(functionalAbility22.getName(), functionalAbility22);
        functionalAbilityArrayList.add(functionalAbility22);

        FunctionalAbility functionalAbility23 = new FunctionalAbility(23, 4, "Problemløsning");
        functionalAbilityHashMap.put(functionalAbility23.getName(), functionalAbility23);
        functionalAbilityArrayList.add(functionalAbility23);

        FunctionalAbility functionalAbility24 = new FunctionalAbility(24, 4, "Anvende kommunikationsudstyr og-teknikker");
        functionalAbilityHashMap.put(functionalAbility24.getName(), functionalAbility24);
        functionalAbilityArrayList.add(functionalAbility24);

        FunctionalAbility functionalAbility25 = new FunctionalAbility(25, 4, "Orienteringsevne");
        functionalAbilityHashMap.put(functionalAbility25.getName(), functionalAbility25);
        functionalAbilityArrayList.add(functionalAbility25);

        FunctionalAbility functionalAbility26 = new FunctionalAbility(26, 4, "Energi og handlekraft");
        functionalAbilityHashMap.put(functionalAbility26.getName(), functionalAbility26);
        functionalAbilityArrayList.add(functionalAbility26);

        FunctionalAbility functionalAbility27 = new FunctionalAbility(27, 4, "Hukommelse");
        functionalAbilityHashMap.put(functionalAbility27.getName(), functionalAbility27);
        functionalAbilityArrayList.add(functionalAbility27);

        FunctionalAbility functionalAbility28 = new FunctionalAbility(28, 4, "Følelsesfunktioner");
        functionalAbilityHashMap.put(functionalAbility28.getName(), functionalAbility28);
        functionalAbilityArrayList.add(functionalAbility28);

        FunctionalAbility functionalAbility29 = new FunctionalAbility(29, 4, "Overordnede kognitive funktioner");
        functionalAbilityHashMap.put(functionalAbility29.getName(), functionalAbility29);
        functionalAbilityArrayList.add(functionalAbility29);

        FunctionalAbility functionalAbility30 = new FunctionalAbility(30, 5, "Have lønnet beskæftigelse");
        functionalAbilityHashMap.put(functionalAbility30.getName(), functionalAbility30);
        functionalAbilityArrayList.add(functionalAbility30);


    }

}


