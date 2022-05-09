package be.Categories;

import java.util.HashMap;

public class TemplateMaps {
    public static HashMap<String, GeneralInfo> getGeneralInfoHashMap(){
        HashMap<String, GeneralInfo> generalInfoHashMap = new HashMap<>();

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

        return generalInfoHashMap;
    }

    public static HashMap<String, HealthCondition> getHealthConditionHashMap(){
        HashMap<String, HealthCondition> healthConditionHashMap = new HashMap<>();

        HealthCondition healthCondition1 = new HealthCondition(1, 1, "Problemer med personlig pleje");
        healthConditionHashMap.put(healthCondition1.getName(), healthCondition1);

        HealthCondition healthCondition2 = new HealthCondition(2, 1, "Problemer med daglige aktiviteter");
        healthConditionHashMap.put(healthCondition2.getName(), healthCondition2);

        HealthCondition healthCondition3 = new HealthCondition(3, 2, "Problemer med mobilitet og bevægelse");
        healthConditionHashMap.put(healthCondition3.getName(), healthCondition3);

        HealthCondition healthCondition4 = new HealthCondition(4, 3, "Problemer med væskeindtag");
        healthConditionHashMap.put(healthCondition4.getName(), healthCondition4);

        HealthCondition healthCondition5 = new HealthCondition(5, 3, "Problemer med fødeindtag");
        healthConditionHashMap.put(healthCondition5.getName(), healthCondition5);

        HealthCondition healthCondition6 = new HealthCondition(6, 3, "Uhensigtsmæssig vægtændring");
        healthConditionHashMap.put(healthCondition6.getName(), healthCondition6);

        HealthCondition healthCondition7 = new HealthCondition(7, 3, "Problemer med overvægt");
        healthConditionHashMap.put(healthCondition7.getName(), healthCondition7);

        HealthCondition healthCondition8 = new HealthCondition(8, 3, "Problemer med undervægt");
        healthConditionHashMap.put(healthCondition8.getName(), healthCondition8);

        HealthCondition healthCondition9 = new HealthCondition(9, 4, "Problemer med kirurgisk sår");
        healthConditionHashMap.put(healthCondition9.getName(), healthCondition9);


        




        HealthCondition healthCondition = new HealthCondition(1, 1, "");
        healthConditionHashMap.put(healthCondition.getName(), healthCondition);









        return healthConditionHashMap;
    }

    public static HashMap<String, FunctionalAbility> getFunctionalAbilityHashMap(){
        HashMap<String, FunctionalAbility> functionalAbilityHashMap = new HashMap<>();


        return functionalAbilityHashMap;
    }
}


