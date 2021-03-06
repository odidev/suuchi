package in.ashwanthkumar.suuchi.store

import java.util

trait ReadStore {
  def get(key: Array[Byte]): Option[Array[Byte]]
}

trait WriteStore {
  def put(key: Array[Byte], value: Array[Byte]): Boolean
  def remove(key: Array[Byte]): Boolean
}

case class KV(key: Array[Byte], value: Array[Byte]) {
  override def equals(obj: scala.Any): Boolean = obj match {
    case o: KV => util.Arrays.equals(key, o.key) && util.Arrays.equals(value, o.value)
    case _     => false
  }
}
trait Scannable {
  def scanner(): Scanner[KV]
}

trait Scanner[T] {
  def prepare(): Unit
  def scan(prefix: Array[Byte]): Iterator[T]
  def scan(): Iterator[T]
  def close(): Unit
}

trait Store extends ReadStore with WriteStore with Scannable
