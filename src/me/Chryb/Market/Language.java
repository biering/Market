package me.Chryb.Market;

public class Language {
	
	public static Market plugin;
	public Language(Market instance){
		 plugin = instance;
	}
	
    /**
    * @param ------
    * @return ::MotDLanguage - PluginLanguage (DE:EN:FR:RU:ITA:BRA:SPA:FR:RU:TR)
    */
	public static MotDLanguage get(){
		if (Config.getLangFile().getString("Language").equalsIgnoreCase("DE")){
			return MotDLanguage.DE;
		}
	        if (Config.getLangFile().getString("Language").equalsIgnoreCase("NL")){
	        	return MotDLanguage.NL;
			}
        if (Config.getLangFile().getString("Language").equalsIgnoreCase("FR")){
        	return MotDLanguage.FR;
		}
        if (Config.getLangFile().getString("Language").equalsIgnoreCase("RU")){
        	return MotDLanguage.RU;
        }
        if (Config.getLangFile().getString("Language").equalsIgnoreCase("ITA")){
        	return MotDLanguage.ITA;
        }
        if (Config.getLangFile().getString("Language").equalsIgnoreCase("BRA")){
        	return MotDLanguage.BRA;
        }
        if (Config.getLangFile().getString("Language").equalsIgnoreCase("SPA")){
        	return MotDLanguage.SPA;
        }
        if (Config.getLangFile().getString("Language").equalsIgnoreCase("FR")){
        	return MotDLanguage.FR;
        }
        if (Config.getLangFile().getString("Language").equalsIgnoreCase("RU")){
        	return MotDLanguage.RU;
        }
        if (Config.getLangFile().getString("Language").equalsIgnoreCase("TR")){
        	return MotDLanguage.TR;
        }
			return MotDLanguage.EN;
	}
	
	public enum MotDLanguage{
		EN,
		DE,
		FR,
		RU,
		ITA,
		BRA,
		SPA,
		TR,
		NL
	}

}
