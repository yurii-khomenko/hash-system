package system.hash.repo

import com.typesafe.scalalogging.LazyLogging
import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.{DigestUtils, MessageDigestAlgorithms}
import system.hash.model.{E164Format, MD5, Metric}

trait HashRepo extends DbRepo with E164Format with Metric with LazyLogging {

  private val algorithm = config.getString("algorithm")
  private val salt = config.getString("salt")

  logger.info(s"used algorithm: $algorithm, supports: ${MessageDigestAlgorithms.values()}")

  private val msisdns = collection.concurrent.TrieMap[MD5, Long]()
//  msisdns(MD5("55c201c6760f2cbc78e674e2f66e453f")) = 380672244089L

  protected def progressSize: Int = ndcs.size * ndcNums

  def getMsisdn(hash: String): Option[Long] = msisdns.get(MD5(hash))

  def loadHashes(): Unit = {

    def writeHash(msisdn: Long): Unit = {
      val digest = getHash(msisdn.toString)
      val hash = MD5(digest)
      assert(!msisdns.contains(hash),
        "Hashes contains duplicate! Pick up different salt!")

      msisdns(hash) = msisdn
      inc()
    }

    for {
      ndc <- ndcs.keys
      number <- toRange(ndc).par
    } writeHash(cc + number)
  }

  def getHash(msisdn: String): String = {
    val digest = DigestUtils.getDigest(algorithm)
    digest.update((msisdn + salt).getBytes)
    Hex.encodeHexString(digest.digest)
  }
}