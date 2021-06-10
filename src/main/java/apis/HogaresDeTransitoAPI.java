package apis;

import apis.dto.HogarDTO;
import apis.dto.HogaresDTO;
import apis.dto.TokenDTO;

import java.util.List;

public interface HogaresDeTransitoAPI {

  public HogaresDTO getHogares(int offset);
  public List<HogarDTO> getHogarList(int offset);
  public TokenDTO getToken(String email, String bearerToken);

}
