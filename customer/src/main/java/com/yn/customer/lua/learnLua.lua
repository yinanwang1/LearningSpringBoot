
ClassName = {}

function ClassName:new(o)
  o = o or {}
  setmetatable(o, self)
  self.__index = self
  return o
end

function ClassName:init()
  print("init")
end

result = ClassName:new({ "好" })
result:init()
print(result)
print(type(result))
