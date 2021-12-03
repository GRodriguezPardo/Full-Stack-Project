package apis;

import apis.dto.HogarDTO;
import apis.dto.HogaresDTO;
import apis.dto.TokenDTO;

import java.util.List;

public interface HogaresDeTransitoAPI {

  HogaresDTO getHogares(String offset);

  List<HogarDTO> getHogarList(String offset);

  TokenDTO getToken(String email, String bearerToken);

}
