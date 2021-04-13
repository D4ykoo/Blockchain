import hashlib

# encode the input to utf-8
input_string = input("Gib eine Zeichenfolge ein: ").encode(encoding='utf-8', errors='strict')

# hash the string with sha-256 and convert it into hex
# it is the basic representation of a hash
hash = hashlib.sha256(input_string).hexdigest()

print(hash)

