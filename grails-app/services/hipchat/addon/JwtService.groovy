package hipchat.addon

import com.nimbusds.jose.JOSEException
import com.nimbusds.jose.crypto.MACVerifier
import com.nimbusds.jwt.SignedJWT
import grails.transaction.Transactional

import java.text.ParseException

@Transactional(readOnly = true)
class JwtService {

    Tenant parseAndVerify(String jwtString) {
        SignedJWT jwt

        try {
            jwt = SignedJWT.parse(jwtString)
        } catch (ParseException pe) {
            pe.printStackTrace()
            return null
        }

        Tenant tenant = Tenant.findByOauthId(jwt.JWTClaimsSet.getClaim('iss')?.toString())

        try {
            jwt.verify(new MACVerifier(tenant.oauthSecret))
            return tenant
        } catch (JOSEException je) {
            je.printStackTrace()
            return null
        }
    }
}
